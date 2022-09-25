package app.Controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Duration;
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
import app.Integracao.Integracao;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerReserva {

    private static IEmprestimoService emservice = new EmprestimoService();
    private static IReservaService rservice = new ReservaService();
    private static IFuncionarioService fservice = new FuncionarioService();
    private static ILeitorService lservice = new LeitorService();
    private static ICopiaService cservice = new CopiaService();
    private static IObraService oservice = new ObraService();
    private static Subject listaEmprestimos = new Subject();

    private static Gson gsonReserva() {
        return new GsonBuilder()
        .registerTypeAdapter(Reserva.class, new AnnotatedDeserializer<Reserva>())
        .create();
    }

    
    public static Route buscarReservasPorUsuario = (Request req, Response res) -> {
        Long idUsuario = Long.parseLong(req.params(":id"));
        List<Reserva> lista = rservice.buscarReservaPorUsuario(idUsuario);
        
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(lista));    
    };
    
    public static Route reservarObra = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonReserva();
        Reserva reserva = gson.fromJson(req.body(), Reserva.class);
        Long idUsuario = Long.parseLong(req.params(":id"));
        Long idCopia = Long.parseLong(req.params(":idCopia"));

        Copia copia = cservice.buscaCopia(idCopia);
        Leitor leitor = lservice.getLeitor(idUsuario);
        Funcionario funcionario = fservice.getFuncionario(reserva.getFuncionarioResponsavel().getId());

        //verifica se as informações são válidas
        if (copia == null || (copia.getObraId() != reserva.getCopia().getObraId()) || leitor == null || funcionario == null){
            return new StandardResponse(StatusResponse.ERROR, "[ERRO] Dados inválidos! Retornando NULL.");

        }
        Obra obra = oservice.buscaObraPorCodigo(copia.getObraId());

        //verifica se o leitor está inscrito em alguma disciplina e se está em algum grupo acadêmico
        int numDisciplina = Integracao.getDisciplina(leitor.getDocumentoId());
        int grupoAtual = Integracao.getGrupo(leitor.getDocumentoId(), 1);
        if(numDisciplina == -1 || grupoAtual == -1){
            return new StandardResponse(StatusResponse.ERROR, "[ERRO] Não foi possível consultar disciplinas/grupos para este RA");
        }
        if(numDisciplina == 0){
            return new StandardResponse(StatusResponse.ERROR, "[EROO] Estudante não está inscrito em disciplinas, não é possível emprestar");
        }
        if(grupoAtual == 0){
            return new StandardResponse(StatusResponse.ERROR, "[EROO] Estudante não está em um grupo acadêmico, não é possível emprestar");
        }

        java.util.Date dataPrevistaRetirada = reserva.getDataPrevistaRetirada();
        java.util.Date dataPrevistaDevolucao = reserva.getDataPrevistaDevolucao();
        java.util.Date dataReserva = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        long diffInMillies = dataPrevistaDevolucao.getTime() - dataPrevistaRetirada.getTime();
        if (diffInMillies < 0){
            return new StandardResponse(StatusResponse.ERROR, "Data de devolução não pode ser anterior a data de retirada");
        }
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);  
        int maxdias = obra.getCategoria().getMaximoDiasEmprestimo();
        if (daysBetween > maxdias ) {
            System.out.print("[ERRO] data prevista de Retirada ultrapassa o máximo de ");
            System.out.print(maxdias);
            System.out.print("depois de "+ dataPrevistaRetirada.toString() +" , retornando null");
            return new StandardResponse(StatusResponse.ERROR, "Data de devolução ultrapassa máximo");
        }
        

        Reserva temp = rservice.buscarPorCopia(copia.getId());

        if (temp != null){
            System.out.println("[Erro] Já há uma reserva feita para essa cópia");
            return new StandardResponse(StatusResponse.ERROR, "[Erro] Já há uma reserva feita para essa cópia");
        }

        if (copia.getState().getState() == "Emprestado") {
            Emprestimo emprestimo = emservice.buscaEmprestimoPorCopia(idCopia);
            java.util.Date dataPrevistaDevolucaoEmpr = emprestimo.getDataPrevistaDevolucao();
            diffInMillies = dataPrevistaRetirada.getTime() - dataPrevistaDevolucaoEmpr.getTime();
            if (diffInMillies < 0){
                return new StandardResponse(StatusResponse.ERROR, "[Erro] Há um empréstimo durante a data prevista de retirada");
            }
        }

        copia.setState(copia.getState().reservar());

        cservice.alterarCopia(copia.getId(), copia);

        //obs o id da reserva nao é usado, o bd gera um
        reserva.setDataReserva(dataReserva);

        new ReservaExpirada(reserva, leitor);

        rservice.realizarReserva(reserva);

        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(reserva));    
    };
}
