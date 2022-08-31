package app;
import static spark.Spark.*;

import com.google.gson.Gson;

import app.Autor.ControllerAutor;
import app.Obra.ControllerObras;

public class Aplication {

    public static void main(String[] args) {

        Gson gson = new Gson();

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));

            // Essas rotas foram utilizadas para teste
            post("/autor",         ControllerAutor.insere, gson::toJson);
            get("/autor",          ControllerAutor.getAutores, gson::toJson);
            get("/autor/:nome",    ControllerAutor.getAutor, gson::toJson);

            // Rotas do ControllerObras
            get("/obra",           ControllerObras.buscaObras, gson::toJson);
            get("/obra/:isbn",     ControllerObras.buscaObra, gson::toJson);
            post("/obra",          ControllerObras.adicionarObra, gson::toJson);
            delete("/obra/:isbn",  ControllerObras.removerObra, gson::toJson);
            post("/autor",         ControllerObras.adicionarAutor, gson::toJson);
            put("/autor/:nome",    ControllerObras.alterarAutor, gson::toJson);
            delete("/autor/:nome", ControllerObras.removerAutor, gson::toJson);
            put("/copia/:codigo/disponibilidade", ControllerObras.mudarDisponibilidadeCopia, gson::toJson);

            // get("/usuario/:ra/dependencias", ControllerUsuario.buscaDependencia);
        });
    }
}
