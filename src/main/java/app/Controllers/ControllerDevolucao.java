package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.DevolucaoService;
import app.Service.spec.IDevolucaoService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerDevolucao {
    
    private static IDevolucaoService service = new DevolucaoService();

    private static Gson gsonDevolucao() {
        return new GsonBuilder()
        .registerTypeAdapter(Devolucao.class, new AnnotatedDeserializer<Devolucao>())
        .create();
    }

    /*
     * Retorna todas as devolucões de um usuário
     */
    public static Route getDevolucoes = (Request req, Response res) -> {
        res.type("application/json");
        Long idUsuario = Long.parseLong(req.params(":idUsuario"));
        List<Devolucao> devolucaos = service.buscaDevolucoes(idUsuario);
        if(devolucaos != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(devolucaos));
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca das devoluções.");
        }
    };

    /*
     * Retorna uma devolucao específica através do id
     */
    public static Route getDevolucao = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id")); 
        Devolucao devolucao = service.buscaDevolucao(id);
        if(devolucao != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(devolucao));
        }else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca da devolucao");
        }
    };

    /*
     * Cria uma nova devolucao
     */
    public static Route criarDevolucao = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonDevolucao();
        Devolucao devolucao = gson.fromJson(req.body(), Devolucao.class);
        if (service.realizarDevolucao(devolucao)) {
            System.out.println("Devolução realizada:");
            System.out.println(new Gson().toJsonTree(devolucao));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na devolução");
        }
    };

    /*
     * Remove uma devolucao
     */
    public static Route removerDevolucao = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo devolucao de ID: " + id);
        if (service.removerDevolucao(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção da devolução, o ID é válido?");
        }
    };

    /*
     * Altera uma devolucao
     */
    public static Route alterarDevolucao = (Request req, Response res) -> {
        res.type("application/json");
        Devolucao devolucao = new Gson().fromJson(req.body(), Devolucao.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alterarDevolucao(id, devolucao)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração da devolução. o ID é válido?");
        }
    };
}
