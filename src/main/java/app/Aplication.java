package app;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import app.Controllers.ControllerEmprestimo;
import app.Controllers.ControllerObras;
import app.Exception.ControllerException;
import app.Controllers.ControllerCategoriaLeitor;
import app.Controllers.ControllerUsuario;
import app.Controllers.ControllerEndereco;

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

            //Rotas de ControllerUsuario
            post("/usuario/:id/leitor", ControllerUsuario.criarLeitor, gson::toJson);
            post("/usuario/:id/funcionario", ControllerUsuario.criarFuncionario, gson::toJson);
            delete("/usuario/:id/leitor", ControllerUsuario.removerLeitor, gson::toJson);
            delete("/usuario/:id/funcionario", ControllerUsuario.removerFuncionario, gson::toJson);
            put("/usuario/:id/leitor", ControllerUsuario.alterarLeitor, gson::toJson);
            put("/usuario/:id/leitor", ControllerUsuario.alterarFuncionario, gson::toJson);

            //Rotas de Endereço
            get("/enderecos", ControllerEndereco.getEnderecos, gson::toJson);
            get("/endereco/:id", ControllerEndereco.getEndereco, gson::toJson);
            post("/endereco", ControllerEndereco.criarEndereco, gson::toJson);
            delete("/endereco", ControllerEndereco.removerEndereco, gson::toJson);
            put("/endereco/:id", ControllerEndereco.alterarEndereco, gson::toJson);
            
            // Exceptions
            exception(NumberFormatException.class, ControllerException.numberFormatException);
            exception(JsonSyntaxException.class, ControllerException.jsonSyntaxException);
            exception(JsonParseException.class, ControllerException.jsonParseException);
        });
    }
}
