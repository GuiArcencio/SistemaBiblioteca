package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.PessoaInteressada;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.ObraService;
import app.Service.impl.PessoaInteressadaService;
import app.Service.spec.IObraService;
import app.Service.spec.IPessoaInteressadaService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerPessoaInteressada {
    
    private static IPessoaInteressadaService service = new PessoaInteressadaService();
    private static IObraService oservice = new ObraService();

    private static Gson gsonPessoaInteressada() {
        return new GsonBuilder()
        .registerTypeAdapter(PessoaInteressada.class, new AnnotatedDeserializer<PessoaInteressada>())
        .create();
    }

    /*
     * Retorna todos objetos "pessoaInteressada" existentes
     */
    public static Route getPessoasInteressadas = (Request req, Response res) -> {
        res.type("application/json");
        List<PessoaInteressada> pessoaInteressadas = service.buscaTodos();
        if(pessoaInteressadas != null){
            for(int i =0; i< pessoaInteressadas.size(); i++){
                System.out.println(pessoaInteressadas.get(i).getStatus());
            }
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(pessoaInteressadas));
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca das pessoaInteressada.");
        }
    };

    /*
     * Retorna todas as pessoas interessadas em uma obra específica através do codigo da obra
     */
    public static Route getPessoaInteressada = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":obraCodigo")); 
        List<Leitor> leitores = service.buscaPessoasPorObra(id);
        if(leitores != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(leitores));
        }else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca dos leitores");
        }
    };

    /*
     * Cria uma nova pessoaInteressada
     */
    public static Route criarPessoaInteressada = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonPessoaInteressada();
        PessoaInteressada pessoaInteressada = gson.fromJson(req.body(), PessoaInteressada.class);
        if (service.insere(pessoaInteressada)) {
            System.out.println("PessoaInteressada inserida:");
            System.out.println(new Gson().toJsonTree(pessoaInteressada));
            Obra obra = oservice.buscaObraPorCodigo(pessoaInteressada.getObraCodigo());
            System.out.println("Add pi no canal de comunicação observer");
            obra.registrar(pessoaInteressada);
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção.");
        }
    };

    /*
     * Remove uma pessoaInteressada
     */
    public static Route removerPessoaInteressada = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo pessoaInteressada de ID: " + id);
        if (service.remove(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção da pessoaInteressada, o ID é válido?");
        }
    };

    /*
     * Altera uma pessoaInteressada
     */
    public static Route alterarPessoaInteressada = (Request req, Response res) -> {
        res.type("application/json");
        PessoaInteressada pessoaInteressada = new Gson().fromJson(req.body(), PessoaInteressada.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.atualiza(id, pessoaInteressada)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração da pessoaInteressada. o ID é válido?");
        }
    };
}
