package app.Controllers;

import java.util.List;

import com.google.gson.Gson;

import app.CategoriaLeitor.CategoriaLeitor;
import app.Service.impl.CategoriaLeitorService;
import app.Service.spec.ICategoriaLeitorService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerCategoriaLeitor {
    
    private static ICategoriaLeitorService service = new CategoriaLeitorService();

    /*
     * Retorna todas as categorias de leitor existentes
     */
    public static Route getCategorias = (Request req, Response res) -> {
        res.type("application/json");
        List<CategoriaLeitor> categorias = service.buscaCategorias();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(categorias));
    };

    /*
     * Cria uma nova categoria de leitor
     */
    public static Route criarCategoria = (Request req, Response res) -> {
        res.type("application/json");
        CategoriaLeitor categoria = new Gson().fromJson(req.body(), CategoriaLeitor.class);
        if (service.insereCategoria(categoria)) {
            System.out.println("Categoria nova inserida:");
            System.out.println(new Gson().toJsonTree(categoria));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção da categoria");
        }
    };

    /*
     * Remove uma categoria de leitor
     */
    public static Route removerCategoria = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo categoria de ID: " + id);
        if (service.removerCategoria(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção da categoria, o ID é válido?");
        }
    };

    /*
     * Altera uma categoria de leitor
     */
    public static Route alterarCategoria = (Request req, Response res) -> {
        res.type("application/json");
        CategoriaLeitor categoria = new Gson().fromJson(req.body(), CategoriaLeitor.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alterarCategoria(id, categoria)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração de categoria. o ID é válido?");
        }
    };
}
