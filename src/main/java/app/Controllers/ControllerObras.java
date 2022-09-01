package app.Controllers;

import java.math.BigInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Obra;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.ObraService;
import app.Service.spec.IObraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerObras {

    private static IObraService service = new ObraService();

    private static Gson gsonObra() {
        return new GsonBuilder()
        .registerTypeAdapter(Obra.class, new AnnotatedDeserializer<Obra>())
        .create();
    }

    private static Gson gsonAutor() {
        return new GsonBuilder()
        .registerTypeAdapter(Autor.class, new AnnotatedDeserializer<Autor>())
        .create();
    }

    /*
     * Busca uma obra por codigo
     */
    public static Route buscaObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        Obra obra = service.buscaObraByCodigo(codigo);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Adiciona uma obra
     */
    public static Route adicionarObra = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonObra();
        Obra obra = gson.fromJson(req.body(), Obra.class);
        service.adicionarObra(obra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove uma obra por codigo
     */
    public static Route removerObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        Obra obra = service.buscaObraByCodigo(codigo);
        service.removerObra(obra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Adiciona um autor
     */
    public static Route adicionarAutor = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonAutor();
        Autor autor = gson.fromJson(req.body(), Autor.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        service.adicionarAutor(codigo, autor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove um autor por nome
     */
    public static Route removerAutor = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        Autor autor = new Gson().fromJson(req.body(), Autor.class);
        service.removerAutor(codigo, autor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Adiciona uma palavra chave a uma obra
     */
    public static Route adicionarPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        service.adicionarPalavraChave(codigo, palavra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove uma palavra chave a uma obra
     */
    public static Route removerPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        service.removerPalavraChave(codigo, palavra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Altera disponibilidade de uma cópia por código
     */
    // TODO: Faz como aqui?
    // public static Route mudarDisponibilidadeCopia = (Request req, Response res) -> {
    //     res.type("application/json");
    // };
}
