package app;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import app.Autor.ControllerAutor;
import app.Controllers.ControllerCategoriaLeitor;
import app.Controllers.ControllerEmprestimo;
import app.Controllers.ControllerObras;
import app.Exception.ControllerException;

public class Aplication {

    public static void main(String[] args) {

        Gson gson = new Gson();

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));

            // Essas rotas foram utilizadas para teste
            post("/autor",       ControllerAutor.insere, gson::toJson);
            get("/autor",        ControllerAutor.getAutores, gson::toJson);
            get("/autor/:nome",  ControllerAutor.getAutor, gson::toJson);

            // Rotas do ControllerObras
            get("/obra/:isbn",             ControllerObras.buscaObra, gson::toJson);
            post("/obra",                  ControllerObras.adicionarObra, gson::toJson);
            delete("/obra/:isbn",          ControllerObras.removerObra, gson::toJson);
            post("/obra/:isbn/autor",      ControllerObras.adicionarAutor, gson::toJson);
            delete("/obra/:isbn/autor",    ControllerObras.removerAutor, gson::toJson);
            post("/obra/:isbn/palavras",   ControllerObras.adicionarPalavraChave, gson::toJson);
            delete("/obra/:isbn/palavras", ControllerObras.removerPalavraChave, gson::toJson);
            // put("/copia/:codigo/disp",     ControllerObras.mudarDisponibilidadeCopia, gson::toJson);

            // Rotas de ControllerEmprestimo
            // get("/usuario/:id/emprestimo",        ControllerEmprestimo.consultarEmprestimo, gson::toJson);
            // get("/usuario/:id/devolucao",         ControllerEmprestimo.consultarDevolucao, gson::toJson);
            // patch("/usuario/:id/emprestimo/:isbn",ControllerEmprestimo.emprestarObra, gson::toJson);
            // patch("/usuario/:id/reserva/:isbn",   ControllerEmprestimo.reservarObra, gson::toJson);
            // patch("/usuario/:id/devolucao/:isbn", ControllerEmprestimo.devolverObra, gson::toJson);

            // Rotas de ControllerCategoriaLeitor
            get("/categoria/leitor",      ControllerCategoriaLeitor.getCategorias, gson::toJson);
            post("/categoria/leitor",     ControllerCategoriaLeitor.criarCategoria, gson::toJson);
            delete("/categoria/leitor/:id", ControllerCategoriaLeitor.removerCategoria, gson::toJson);
            put("/categoria/leitor/:id",  ControllerCategoriaLeitor.alterarCategoria, gson::toJson);

            // get("/usuario/:ra/dependencias", ControllerUsuario.buscaDependencia);
            
            // Exceptions
            exception(NumberFormatException.class, ControllerException.numberFormatException);
            exception(JsonSyntaxException.class, ControllerException.jsonSyntaxException);
        });
    }
}
