package app.Controllers;

import java.math.BigInteger;

import com.google.gson.Gson;

import app.Leitor.*;
import app.Usuario.*;
import app.dao.UsuairoDAO;
import app.dao.LeitorDAO;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerUsuario {

    private UsuarioDAO uDAO;
    private LeitorDAO lDAO;

    
    public void init() {
        uDAO = new UsuarioDAO();
        lDAO = new LeitorDAO();
    }

    /*
     * Cria um Leitor
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Leitor leitor = new Gson().fromJson(req.body(), Leitor.class);
        lDAO.insert(leitor);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Cria um Funcionario
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Leitor leitor = new Gson().fromJson(req.body(), Leitor.class);
        lDAO.insert(leitor);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Cria um 
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Leitor leitor = new Gson().fromJson(req.body(), Leitor.class);
        lDAO.insert(leitor);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Remove um Leitor
     */
    public static Route removerAutor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = req.params(":id");
        Leitor leitor = new leitor(id);
        lDAO.delete(leitor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };
    
}
