package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.RelObraAutor;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.RelObraAutorService;
import app.Service.spec.IRelObraAutorService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerRelObraAutor {
    private static IRelObraAutorService service = new RelObraAutorService();

    private static Gson gsonRelObraAutor(){
        return new GsonBuilder()
        .registerTypeAdapter(RelObraAutor.class, new AnnotatedDeserializer<RelObraAutor>())
        .create();
    }

    public static Route getObrasPorAutor = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo_autor = Long.parseLong(req.params(":codigo_autor"));
        List<Obra> listaObras = service.buscaObrasPorAutor(codigo_autor);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(listaObras));
    };

    public static Route getAutoresPorObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo_obra = Long.parseLong(req.params(":codigo_obra"));
        List<Autor> listaAutores = service.buscaAutoresPorObra(codigo_obra);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(listaAutores));
    };

    public static Route postROA = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonRelObraAutor();
        RelObraAutor roa = gson.fromJson(req.body(), RelObraAutor.class);
        service.adicionaROA(roa);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    public static Route deleteROA = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        service.removeROA(id);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    public static Route putROA = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        Gson gson = gsonRelObraAutor();
        RelObraAutor roa = gson.fromJson(req.body(), RelObraAutor.class);
        service.atualizaROA(id, roa);
        return new StandardResponse(StatusResponse.SUCCESS);
    };
}
