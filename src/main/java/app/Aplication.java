package app;
import static spark.Spark.*;

import app.Autor.ControllerAutor;

public class Aplication {

    public static void main(String[] args) {

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));
            
            post("/autor",      ControllerAutor.insere);
            get("/autor",       ControllerAutor.getAutores);
            get("/autor/:nome", ControllerAutor.getAutor);
        });
    }
}
