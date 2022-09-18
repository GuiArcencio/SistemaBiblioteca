package app;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import app.Controllers.ControllerEmprestimo;
import app.Controllers.ControllerFuncionario;
import app.Controllers.ControllerLeitor;
import app.Controllers.ControllerObras;
import app.Exception.ControllerException;
import app.Controllers.ControllerAutor;
import app.Controllers.ControllerCategoriaLeitor;

public class Aplication {

    public static void main(String[] args) {

        Gson gson = new Gson();

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));

            // Rotas do ControllerObras
            get("/obra/:codigo",             ControllerObras.buscaObra, gson::toJson);
            post("/obra",                  ControllerObras.adicionarObra, gson::toJson);
            delete("/obra/:codigo",          ControllerObras.removerObra, gson::toJson);
            post("/obra/:codigo/autor",      ControllerObras.adicionarAutor, gson::toJson);
            delete("/obra/:codigo/autor",    ControllerObras.removerAutor, gson::toJson);
            post("/obra/:codigo/palavras",   ControllerObras.adicionarPalavraChave, gson::toJson);
            delete("/obra/:codigo/palavras", ControllerObras.removerPalavraChave, gson::toJson);
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
            
            // Rotas de ControllerAutor
            get("/autor",      ControllerAutor.getAutores, gson::toJson);
            post("/autor",     ControllerAutor.criarAutor, gson::toJson);
            delete("/autor/:id", ControllerAutor.removerAutor, gson::toJson);
            put("/autor/:id",  ControllerAutor.alterarAutor, gson::toJson);

            // TODO: 
            // Rotas de ControllerLeitor
            post("/usuario/leitor", ControllerLeitor.criarLeitor, gson::toJson);
            delete("/usuario/leitor/:id", ControllerLeitor.removerLeitor, gson::toJson);
            put("/usuario/leitor/:id", ControllerLeitor.alterarLeitor, gson::toJson);

            // Rotas de ControllerFuncionario
            post("/usuario/funcionario", ControllerFuncionario.criarFuncionario, gson::toJson);
            delete("/usuario/funcionario/:id", ControllerFuncionario.removerFuncionario, gson::toJson);
            put("/usuario/funcionario/:id", ControllerFuncionario.alterarFuncionario, gson::toJson);

            
            // Exceptions
            exception(NumberFormatException.class, ControllerException.numberFormatException);
            exception(JsonSyntaxException.class, ControllerException.jsonSyntaxException);
            exception(JsonParseException.class, ControllerException.jsonParseException);
        });
    }
}
