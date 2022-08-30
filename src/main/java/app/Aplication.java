package app;
import static spark.Spark.*;

import app.Autor.ControllerAutor;
import app.Obra.ControllerObras;

public class Aplication {

    public static void main(String[] args) {

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));

            // Essas rotas foram utilizadas para teste
            post("/autor",         ControllerAutor.insere);
            get("/autor",          ControllerAutor.getAutores);
            get("/autor/:nome",    ControllerAutor.getAutor);

            // Rotas do ControllerObras
            get("/obra",           ControllerObras.buscaObras);
            get("/obra/:isbn",     ControllerObras.buscaObra);
            post("/obra",          ControllerObras.adicionarObra);
            delete("/obra/:isbn",  ControllerObras.removerObra);
            post("/autor",         ControllerObras.adicionarAutor);
            put("/autor/:nome",    ControllerObras.alterarAutor);
            delete("/autor/:nome", ControllerObras.removerAutor);
            put("/copia/:codigo/disponibilidade", ControllerObras.mudarDisponibilidadeCopia);
        });
    }
}
