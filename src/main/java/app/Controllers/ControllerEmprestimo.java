package app.Controllers;

import java.math.BigInteger;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Estados.Emprestado;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.EmprestimoService;
import app.Service.spec.IEmprestimoService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerEmprestimo {
    
    private static IEmprestimoService service = new EmprestimoService();

    private static Gson gsonEmprestimo() {
        return new GsonBuilder()
        .registerTypeAdapter(Emprestimo.class, new AnnotatedDeserializer<Emprestimo>())
        .create();
    }

    // /*
    //  * Recebe Usuário
    //  * Retorna empréstimos ligados a ele
    //  */
    // public static Route consultarEmprestimo = (Request req, Response res) -> {
    //     res.type("application/json");
    //     BigInteger id = new BigInteger(req.params(":id"));
    //     return new StandardResponse(StatusResponse.SUCCESS, new Gson()
    //         .toJsonTree(service.buscaEmprestimos(id)));
    // };

    // /*
    //  * Recebe Usuário
    //  * Retorna devoluções ligadas a ele
    //  */
    // public static Route consultarDevolucao = (Request req, Response res) -> {
    //     res.type("application/json");
    //     BigInteger id = new BigInteger(req.params(":id"));
    //     return new StandardResponse(StatusResponse.SUCCESS, new Gson()
    //         .toJsonTree(service.buscarDevolucao(id)));
    // };

    // /*
    //  * Recebe Usuário e Obra
    //  * Se existir uma cópia disponível da obra, empresta ela
    //  * Prioridade de empréstimos segue: Cópias Reservadas, Cópias Disponíveis
    //  */
    // public static Route emprestarObra = (Request req, Response res) -> {
    //     res.type("application/json");
    //     BigInteger id = new BigInteger(req.params(":id"));
    //     BigInteger isbn = new BigInteger(req.params(":isbn"));
    //     return new StandardResponse(StatusResponse.SUCCESS, new Gson()
    //         .toJsonTree(service.emprestarObra(id, isbn)));
    // };

    // /*
    //  * Recebe Usuário e Obra
    //  * Se existir uma cópia emprestada da obra com o usuário, devolve ela
    //  */
    // public static Route devolverObra = (Request req, Response res) -> {
    //     res.type("application/json");
    //     BigInteger id = new BigInteger(req.params(":id"));
    //     BigInteger isbn = new BigInteger(req.params(":isbn"));
    //     return new StandardResponse(StatusResponse.SUCCESS, new Gson()
    //         .toJsonTree(service.devolverObra(id, isbn)));
    // };

    // /*
    //  * Recebe Usuário e Obra
    //  * Se existir uma cópia disponível da obra, reserva ela
    //  */
    // public static Route reservarObra = (Request req, Response res) -> {
    //     res.type("application/json");
    //     BigInteger id = new BigInteger(req.params(":id"));
    //     BigInteger isbn = new BigInteger(req.params(":isbn"));
    //     return new StandardResponse(StatusResponse.SUCCESS, new Gson()
    //         .toJsonTree(service.emprestarObra(id, isbn)));
    // };
}
