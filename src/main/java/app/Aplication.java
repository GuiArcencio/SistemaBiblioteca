package app;
import static spark.Spark.*;

import app.Autor.ControllerAutor;
import app.Obra.ControllerObras;

public class Aplication {

    public static void main(String[] args) {

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));
            
            // Essas rotas foram utilizadas para teste
            post("/autor",      ControllerAutor.insere);
            get("/autor",       ControllerAutor.getAutores);
            get("/autor/:nome", ControllerAutor.getAutor);

            // Rotas do ControllerObras
            // // Busca todas as Obras
            // get("/obra", ControllerObras.buscaObras);
            // // Busca uma obra por ISBN
            // get("/obra/:isbn", ControllerObras.buscaObra);
            // // Adiciona uma obra
            // post("/obra", ControllerObras.adicionarObra);
            // // Remove uma obra por ISBN
            // delete("/obra/:isbn", ControllerObras.removerObra);
            // // Adiciona um autor
            // post("/autor", ControllerObras.adicionarAutor);
            // // Altera um autor
            // put("/autor/:nome", ControllerObras.alterarAutor);
            // // Remove um autor por nome
            // delete("/autor/:nome", ControllerObras.removerAutor);
            // // Altera disponibilidade de uma cópia por código
            // put("/copia/:codigo/disponibilidade", ControllerObras.mudarDisponibilidadeCopia);
        });
    }
}
