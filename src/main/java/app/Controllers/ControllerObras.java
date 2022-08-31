package app.Controllers;

import java.math.BigInteger;

import com.google.gson.Gson;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Obra;
import app.Service.impl.ObraService;
import app.Service.spec.IObraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerObras {

    private static IObraService service = new ObraService();

    /*
     * Busca uma obra por ISBN
     */
    public static Route buscaObra = (Request req, Response res) -> {
        res.type("application/json");
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        Obra obra = service.buscaObra(isbn);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Adiciona uma obra
     */
    public static Route adicionarObra = (Request req, Response res) -> {
        res.type("application/json");
        Obra obra = new Gson().fromJson(req.body(), Obra.class);
        service.adicionarObra(obra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove uma obra por ISBN
     */
    public static Route removerObra = (Request req, Response res) -> {
        res.type("application/json");
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        Obra obra = service.buscaObra(isbn);
        service.removerObra(obra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Adiciona um autor
     */
    public static Route adicionarAutor = (Request req, Response res) -> {
        res.type("application/json");
        Autor autor = new Gson().fromJson(req.body(), Autor.class);
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        service.adicionarAutor(isbn, autor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove um autor por nome
     */
    public static Route removerAutor = (Request req, Response res) -> {
        res.type("application/json");
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        Autor autor = new Gson().fromJson(req.body(), Autor.class);
        service.removerAutor(isbn, autor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Adiciona uma palavra chave a uma obra
     */
    public static Route adicionarPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        service.adicionarPalavraChave(isbn, palavra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove uma palavra chave a uma obra
     */
    public static Route removerPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        BigInteger isbn = new BigInteger(req.params(":isbn"));
        service.removerPalavraChave(isbn, palavra);
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
