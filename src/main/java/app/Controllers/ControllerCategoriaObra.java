package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.CategoriaObra;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.CategoriaObraService;
import app.Service.spec.ICategoriaObraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerCategoriaObra {
    
    private static ICategoriaObraService service = new CategoriaObraService();

    private static Gson gsonCategoriaObra() {
        return new GsonBuilder()
        .registerTypeAdapter(CategoriaObra.class, new AnnotatedDeserializer<CategoriaObra>())
        .create();
    }

    /*
     * Retorna todas as categoriaObras existentes
     */
    public static Route getCategoriasObra = (Request req, Response res) -> {
        res.type("application/json");
        List<CategoriaObra> categoriaObras = service.buscaCategoriasObra();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(categoriaObras));
    };

    /*
     * Retorna uma categoriaObra específica através do codigo
     */
    public static Route getCategoriaObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo")); 
        CategoriaObra categoriaObra = service.buscaCategoriaObraPorCodigo(codigo);
        if(categoriaObra != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(categoriaObra));
        }else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca da categoriaObra");
        }
    };

    /*
     * Cria uma nova categoriaObra
     */
    public static Route criarCategoriaObra = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonCategoriaObra();
        CategoriaObra categoriaObra = gson.fromJson(req.body(), CategoriaObra.class);
        if (service.insereCategoriaObra(categoriaObra)) {
            System.out.println("CategoriaObra inserida:");
            System.out.println(new Gson().toJsonTree(categoriaObra));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção da categoriaObra");
        }
    };

    /*
     * Remove uma categoriaObra
     */
    public static Route removerCategoriaObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        System.out.println("Removendo categoriaObra de ID: " + codigo);
        if (service.removeCategoriaObra(codigo)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção da categoriaObra, o ID é válcodigoo?");
        }
    };

    /*
     * Altera uma categoriaObra
     */
    public static Route alterarCategoriaObra = (Request req, Response res) -> {
        res.type("application/json");
        CategoriaObra categoriaObra = new Gson().fromJson(req.body(), CategoriaObra.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        if (service.alteraCategoriaObra(codigo, categoriaObra)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração da categoriaObra. o ID é válcodigoo?");
        }
    };
}
