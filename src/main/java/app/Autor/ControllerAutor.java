package app.Autor;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerAutor {
    
    public static Route insere = (Request request, Response response) -> {
        System.out.println("Inserindo");
        response.type("application/json");
        // Autor autor = new Gson().fromJson(request.body(), Autor.class);
        // TODO: Autor Service, DAO e tudo mais
        // autorService.addAutor(autor);

        return new StandardResponse(StatusResponse.SUCCESS);
    };

    public static Route getAutores = (Request request, Response response) -> {
        response.type("application/json");

        // Testando sem um banco de dados //
        List<Autor> autores = new ArrayList<Autor>();
        autores.add(new Autor("Cláudio Vanesso", "CV"));
        autores.add(new Autor("Francisco Morato", "FM"));
        // Testando sem um banco de dados //

        return new StandardResponse(StatusResponse.SUCCESS, new Gson()
                // .toJsonTree(autorService.getAutores())
                .toJsonTree(autores)
        );
    };

    public static Route getAutor = (Request request, Response response) -> {
        response.type("application/json");

        // Testando sem um banco de dados
        Autor autor1 = new Autor("Cláudio Vanesso", "CV");
        // Testando sem um banco de dados

        return new StandardResponse(StatusResponse.SUCCESS, new Gson()
                // .toJsonTree(autorService.getAutor(request.params(":nome")))
                .toJsonTree(autor1)
        );
    };

    // TODO: Implementar REST

        // put("/autor/:id", (request, response) -> {
        //     //...
        // });
        // delete("/autor/:id", (request, response) -> {
        //     //...
        // });
        // options("/autor/:id", (request, response) -> {
        //     //...
        // });
}
