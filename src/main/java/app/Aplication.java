package app;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import app.Exception.ControllerException;
import app.Controllers.*;

public class Aplication {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/hello", (req, res) -> "Olá Heroku");

        Gson gson = new Gson();
        boolean autenticacao = true;
        
        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Chamada API recebida"));
            /*
             * Middleware de autenticação
             */
            if (autenticacao)
                before("/*", (req, res) -> { ControllerAutenticacao.middlewareAutenticacao(req, res); });

            // Rotas do ControllerObras
            get("/obras", ControllerObras.buscaObras, gson::toJson);
            get("/obra/:codigo",             ControllerObras.buscaObra, gson::toJson);
            post("/obra",                  ControllerObras.adicionarObra, gson::toJson);
            delete("/obra/:codigo",          ControllerObras.removerObra, gson::toJson);
            //post("/obra/:codigo/autor",      ControllerObras.adicionarAutor, gson::toJson);
            //delete("/obra/:codigo/autor",    ControllerObras.removerAutor, gson::toJson);
            post("/obra/:codigo/palavras",   ControllerObras.adicionarPalavraChave, gson::toJson);
            delete("/obra/:codigo/palavras", ControllerObras.removerPalavraChave, gson::toJson);
            get("/obra/:codigo/copias", ControllerObras.buscarCopiasDisponiveis, gson::toJson);
            post("/obra/copia", ControllerObras.adicionarCopiaDeObra, gson::toJson);
            delete("/obra/:codigo/copia/:id", ControllerObras.removerCopiaDeObra, gson::toJson);

            //Rotas do ControllerRelObraAutor
            get("/autor/:codigo_autor/obras", ControllerRelObraAutor.getObrasPorAutor, gson::toJson);
            get("/obra/:codigo_obra/autores", ControllerRelObraAutor.getAutoresPorObra, gson::toJson);
            post("/autor&obra", ControllerRelObraAutor.postROA, gson::toJson);
            delete("/autor&obra/:id", ControllerRelObraAutor.deleteROA, gson::toJson);
            put("/autor&obra/:id", ControllerRelObraAutor.putROA, gson::toJson);

            // Rotas de ControllerEmprestimo
            get("/usuario/:id/emprestimo",        ControllerEmprestimos.buscaEmprestimosPorUsuario, gson::toJson);
            // get("/usuario/:id/devolucao",         ControllerEmprestimo.consultarDevolucao, gson::toJson);
            post("/usuario/:id/emprestimo/:idCopia",ControllerEmprestimos.emprestarObra, gson::toJson);
            get("/usuario/:id/reservas", ControllerReserva.buscarReservasPorUsuario, gson::toJson);
            post("/usuario/:id/reserva/:isbn",   ControllerReserva.reservarObra, gson::toJson);
            // patch("/usuario/:id/devolucao/:isbn", ControllerEmprestimo.devolverObra, gson::toJson);
            get("/usuario/:cpf/pendencias", ControllerEmprestimos.buscaPendenciasPorUsuario, gson::toJson);

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

            // Rotas de ControllerLeitor
            get("/usuario/leitores", ControllerLeitor.buscaLeitores, gson::toJson);
            get("/usuario/leitor/:id", ControllerLeitor.buscaLeitor, gson::toJson);
            post("/usuario/leitor", ControllerLeitor.criarLeitor, gson::toJson);
            delete("/usuario/leitor/:id", ControllerLeitor.removerLeitor, gson::toJson);
            put("/usuario/leitor/:id", ControllerLeitor.alterarLeitor, gson::toJson);

            // Rotas de ControllerFuncionario
            get("/usuario/funcionario", ControllerFuncionario.buscaFuncionarios, gson::toJson);
            get("/usuario/funcionario/:id", ControllerFuncionario.buscaFuncionario, gson::toJson);
            post("/usuario/funcionario", ControllerFuncionario.criarFuncionario, gson::toJson);
            delete("/usuario/funcionario/:id", ControllerFuncionario.removerFuncionario, gson::toJson);
            put("/usuario/funcionario/:id", ControllerFuncionario.alterarFuncionario, gson::toJson);

            //Rotas de Endereço
            get("/enderecos", ControllerEndereco.getEnderecos, gson::toJson);
            get("/endereco/:id", ControllerEndereco.getEndereco, gson::toJson);
            post("/endereco", ControllerEndereco.criarEndereco, gson::toJson);
            delete("/endereco/:id", ControllerEndereco.removerEndereco, gson::toJson);
            put("/endereco/:id", ControllerEndereco.alterarEndereco, gson::toJson);

            //Rotas de Editora
            get("/editoras", ControllerEditora.getEditoras, gson::toJson);
            get("/editora/:id", ControllerEditora.getEditora, gson::toJson);
            post("/editora", ControllerEditora.criarEditora, gson::toJson);
            delete("/editora/:id", ControllerEditora.removerEditora, gson::toJson);
            put("/editora/:id", ControllerEditora.alterarEditora, gson::toJson);

            //Rotas de CategoriaObra
            get("/categoriasObra", ControllerCategoriaObra.getCategoriasObra, gson::toJson);
            get("/categoriaObra/:codigo", ControllerCategoriaObra.getCategoriaObra, gson::toJson);
            post("/categoriaObra", ControllerCategoriaObra.criarCategoriaObra, gson::toJson);
            delete("/categoriaObra/:codigo", ControllerCategoriaObra.removerCategoriaObra, gson::toJson);
            put("/categoriaObra/:codigo", ControllerCategoriaObra.alterarCategoriaObra, gson::toJson);
            
            //Rotas de PessoaInteressada
            get("/pessoasInteressadas", ControllerPessoaInteressada.getPessoasInteressadas, gson::toJson);
            get("/pessoaInteressada/:obraCodigo", ControllerPessoaInteressada.getPessoaInteressada, gson::toJson);
            post("/pessoaInteressada", ControllerPessoaInteressada.criarPessoaInteressada, gson::toJson);
            delete("/pessoaInteressada/:id", ControllerPessoaInteressada.removerPessoaInteressada, gson::toJson);
            put("/pessoaInteressada/:id", ControllerPessoaInteressada.alterarPessoaInteressada, gson::toJson);

            //Rotas de Devolução
            get("/usuario/:idUsuario/devolucoes", ControllerDevolucao.getDevolucoes, gson::toJson);
            get("/usuario/devolucao/:id", ControllerDevolucao.getDevolucao, gson::toJson);
            post("/devolucao", ControllerDevolucao.criarDevolucao, gson::toJson);
            delete("/usuario/devolucao/:id", ControllerDevolucao.removerDevolucao, gson::toJson);
            put("/usuario/devolucao/:id", ControllerDevolucao.alterarDevolucao, gson::toJson);

            // Exceptions
            exception(NumberFormatException.class, ControllerException.numberFormatException);
            exception(JsonSyntaxException.class, ControllerException.jsonSyntaxException);
            exception(JsonParseException.class, ControllerException.jsonParseException);
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
