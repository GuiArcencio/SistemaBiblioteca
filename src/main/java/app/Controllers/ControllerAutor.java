package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Autor;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.AutorService;
import app.Service.spec.IAutorService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerAutor {
    
    private static IAutorService service = new AutorService();

    private static Gson gsonAutor() {
        return new GsonBuilder()
        .registerTypeAdapter(Autor.class, new AnnotatedDeserializer<Autor>())
        .create();
    }

    /*
     * Retorna todos os Autores existentes
     */
    public static Route getAutores = (Request req, Response res) -> {
        res.type("application/json");
        List<Autor> autores = service.listaAutores();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(autores));
    };

    /*
     * Cria um novo Autor
     */
    public static Route criarAutor = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonAutor();
        Autor autor = gson.fromJson(req.body(), Autor.class);
        System.out.println(autor.getNome());
        if (service.insereAutor(autor)) {
            System.out.println("Autor novo inserido:");
            System.out.println(new Gson().toJsonTree(autor));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção de Autor");
        }
    };

    /*
     * Remove um Autor
     */
    public static Route removerAutor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo Autor de ID: " + id);
        if (service.removerAutor(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção de Autor, o ID é válido?");
        }
    };

    /*
     * Altera uma Autore de leitor
     */
    public static Route alterarAutor = (Request req, Response res) -> {
        res.type("application/json");
        Autor autor = new Gson().fromJson(req.body(), Autor.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alterarAutor(id, autor)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração de Autor. o ID é válido?");
        }
    };
}
