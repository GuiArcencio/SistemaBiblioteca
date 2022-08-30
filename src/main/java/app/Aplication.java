package app;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import app.Autor.Autor;
import app.Autor.AutorService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;

public class Aplication {

    private AutorService autorService;

    public static void main(String[] args) {

        //// Inserção de autor
        post("/autor", (request, response) -> {
            response.type("application/json");
            Autor autor = new Gson().fromJson(request.body(), Autor.class);
            // TODO: Autor Service, DAO e tudo mais
            // autorService.addAutor(autor);

            return new Gson()
                .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //// Obter todos os autores
        get("/autor", (request, response) -> {
            response.type("application/json");

            // Testando sem um banco de dados //
            List<Autor> autores = new ArrayList<Autor>();
            autores.add(new Autor("Cláudio Vanesso", "CV"));
            autores.add(new Autor("Francisco Morato", "FM"));
            // Testando sem um banco de dados //

            return new Gson().toJson(
                new StandardResponse(StatusResponse.SUCCESS, new Gson()
                    // .toJsonTree(autorService.getAutores())
                    .toJsonTree(autores)
                )
            );
        });
        
        //// Obter autor por nome
        get("/autor/:nome", (request, response) -> {
            response.type("application/json");

            // Testando sem um banco de dados
            Autor autor1 = new Autor("Cláudio Vanesso", "CV");
            // Testando sem um banco de dados

            return new Gson().toJson(
                new StandardResponse(StatusResponse.SUCCESS, new Gson()
                    // .toJsonTree(autorService.getAutor(request.params(":nome")))
                    .toJsonTree(autor1)
                )
            );
        });

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
}
