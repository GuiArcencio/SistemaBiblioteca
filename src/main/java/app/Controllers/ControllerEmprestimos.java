package app.Controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.formatter.qual.ReturnsFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteEntradaSaidaObras.Reserva;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.SubjectObserver.EmprestimoAtrasado;
import app.Domain.SubjectObserver.ReservaExpirada;
import app.Domain.SubjectObserver.Subject;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.CopiaService;
import app.Service.impl.DevolucaoService;
import app.Service.impl.EmprestimoService;
import app.Service.impl.ReservaService;
import app.Service.impl.FuncionarioService;
import app.Service.impl.LeitorService;
import app.Service.impl.ObraService;
import app.Service.spec.ICopiaService;
import app.Service.spec.IDevolucaoService;
import app.Service.spec.IEmprestimoService;
import app.Service.spec.IFuncionarioService;
import app.Service.spec.IReservaService;
import app.Service.spec.ILeitorService;
import app.Service.spec.IObraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerEmprestimos {

    private static IEmprestimoService emservice = new EmprestimoService();
    private static IDevolucaoService deservice = new DevolucaoService();
    private static IReservaService rservice = new ReservaService();
    private static IFuncionarioService fservice = new FuncionarioService();
    private static ILeitorService lservice = new LeitorService();
    private static ICopiaService cservice = new CopiaService();
    private static IObraService oservice = new ObraService();
    private static Subject listaEmprestimos = new Subject();

    private static Gson gsonEmprestimo() {
        return new GsonBuilder()
        .registerTypeAdapter(Emprestimo.class, new AnnotatedDeserializer<Emprestimo>())
        .create();
    }
    
    public static Route buscaEmprestimosPorUsuario = (Request req, Response res) -> {
        Long idUsuario = Long.parseLong(req.params(":id"));
        List<Emprestimo> lista = emservice.buscaEmprestimos(idUsuario);
        if(lista != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(lista));
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Não há empréstimos ou Usuario não encontrado");
        }
    };

    /*
     * Busca por emprestimos atrasados e retorna a quantidade de pendencias
     */
    public static Route buscaPendenciasPorUsuario = (Request req, Response res) -> {
        Long idUsuario = Long.parseLong(req.params(":cpf"));
        List<Emprestimo> lista = emservice.buscaEmprestimos(idUsuario);
        int acm = 0;
        for (Emprestimo emprestimo : lista){
            if (emprestimo.getAtrasado()){
                //emprestimo atrasado
                System.out.println("aqui");
                acm += 1;
            }
        }
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(acm));
    };

    /*
     * Realiza o Emprestimo de uma Obra
     */
    public static Route emprestarObra = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonEmprestimo();
        Emprestimo emprestimo = gson.fromJson(req.body(), Emprestimo.class);
        Long idUsuario = Long.parseLong(req.params(":id"));
        
        Copia copia = emprestimo.getCopia();
        Leitor leitor = emprestimo.getLeitor();
        Funcionario funcionario = new Funcionario(emprestimo.getFuncionarioResponsavel().getId());
        Obra obra = oservice.buscaObraPorCodigo(emprestimo.getCopia().getObraId());
        copia = cservice.buscaCopia(copia.getId());
        leitor = lservice.getLeitor(idUsuario);
        funcionario = fservice.getFuncionario(funcionario.getId());
        
        //verifica se as informações são válidas
        if (copia == null || (copia.getObraId() != emprestimo.getCopia().getObraId()) || leitor == null || funcionario == null){
            return new StandardResponse(StatusResponse.ERROR, "[ERRO] Dados inválidos! Retornando NULL.");

        }
        
        LocalDate dataDevolucao = obra.getCategoria().calculaDataDevolucao();
        java.util.Date dataPrevistaDevolucao = java.util.Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dataEmprestimo = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        

        //obs o id do emprestimo nao é usado, o bd gera um
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataPrevistaDevolucao(dataPrevistaDevolucao);
        emprestimo.setAtrasado(false);
        

        
        //verifica se há uma reserva antes de realizar o emprestimo
        Reserva reserva = rservice.buscarPorLeitorECopia(idUsuario, copia.getId());
        if(reserva != null){
            //caso exista uma reserva e não seja deste leitor
            if(reserva.getLeitor().getId() != idUsuario) {
                return new StandardResponse(StatusResponse.ERROR, "[ERRO] Cópia já está reservada por outro usuário, não é possível emprestar!");
            }

            //caso a reserva seja deste leitor
            else{
                //checa o estado da cópia, se for emprestado não pode realizar a reserva
                if(copia.getState().getState() == "Emprestado"){
                    return new StandardResponse(StatusResponse.ERROR, "[ERRO] Copia ainda está emprestada, espere o término do empŕestimo");
                }
               
                //criando um emprestimo
                new EmprestimoAtrasado(emprestimo, reserva.getLeitor());
                emservice.realizarEmprestimo(emprestimo);
                copia.emprestar();
                cservice.alterarCopia(copia.getId(), copia);
                new EmprestimoAtrasado(listaEmprestimos, leitor);
                return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(emprestimo));
            }
        }
        

        if(copia.getState().getState() != "Disponivel") {
            return new StandardResponse(StatusResponse.ERROR, "[ERRO] Copia não está disponível, impossível emprestar!");
        }
        
        
        emservice.realizarEmprestimo(emprestimo);
        copia.emprestar();
        cservice.alterarCopia(copia.getId(), copia);
        new EmprestimoAtrasado(listaEmprestimos, leitor);
        listaEmprestimos.notifyAllObservers();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(emprestimo));
    };
    
}
