package app.Exception;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class ControllerException {

    public static ExceptionHandler<NumberFormatException> numberFormatException = (NumberFormatException e, Request req, Response res) -> {
        System.out.println("[ERRO] É possível que o usuário tenha digitado um número inválido:");
        System.out.println(e.getMessage());
        res.status(400);
        res.body(
            new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Número inválido"))
        );
    };

    public static ExceptionHandler<JsonSyntaxException> jsonSyntaxException = (JsonSyntaxException e, Request req, Response res) -> {
        System.out.println("[ERRO] Falha na sintaxe do JSON:");
        System.out.println("===\n" + req.body() + "\n===");
        System.out.println(e.getMessage());
        res.status(400);
        res.body(
            new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "JSON recebido inválido"))
        );
    };

    public static ExceptionHandler<JsonParseException> jsonParseException = (JsonParseException e, Request req, Response res) -> {
        System.out.println("[ERRO] Falha na sintaxe do JSON:");
        System.out.println("===\n" + req.body() + "\n===");
        System.out.println(e.getMessage());
        res.status(400);
        res.body(
            new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "JSON recebido inválido"))
        );
    };
}
