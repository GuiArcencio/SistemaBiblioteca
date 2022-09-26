package app.Controllers;

import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Exception.AnnotatedDeserializer;
import app.Domain.PacoteUsuarios.Leitor;
import app.Service.impl.LeitorService;
import app.Service.spec.ILeitorService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerLeitor {

    
    private static ILeitorService service = new LeitorService();

    private static Gson gsonLeitor() {
        return new GsonBuilder()
        .registerTypeAdapter(Leitor.class, new AnnotatedDeserializer<Leitor>())
        .create();
    }

    /*
     * Busca um Leitores
     */
    public static Route buscaLeitores = (Request req, Response res) -> {
        res.type("application/json");
        List<Leitor> usuarios = service.getLeitores();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(usuarios));
    };

    /*
     * Busca um Leitor
     */
    public static Route buscaLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        Leitor leitor = service.getLeitor(id);
        if (leitor!= null)
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(leitor));
        else
            return new StandardResponse(StatusResponse.ERROR, "Leitor nao encontrado");
    };

    /*
     * Busca um Leitor pelo documento
     */
    public static Route getLeitorPorDocumento = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":documentoId"));
        Leitor leitor = service.buscaPorDocumento(id);
        if (leitor!= null)
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(leitor));
        else
            return new StandardResponse(StatusResponse.ERROR, "Leitor nao encontrado");
    };

    /*
     * Cria um Leitor
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonLeitor();
        Leitor leitor = gson.fromJson(req.body(), Leitor.class);
        Long cLeitorId = Long.parseLong(gson.fromJson(req.body(), Properties.class).getProperty("categoriaLeitor"));
        System.out.println(cLeitorId);
       
        if(service.insereLeitor(leitor)){
            System.out.println("Novo Leitor inserido:");
            System.out.println(new Gson().toJsonTree(leitor));
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(leitor));
        }else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do leitor");
        }
    };

     /*
     * Remove um Leitor
     */
    public static Route removerLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo Leitor de ID: " + id);
        if (service.removeLeitor(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção do leitor, o ID é válido?");
        }
    };

    /*
     * Altera um Leitor
     */
    public static Route alterarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Leitor leitor = new Gson().fromJson(req.body(), Leitor.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alteraLeitor(id, leitor)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração do leitor. o ID é válido?");
        }
    };
    
}
