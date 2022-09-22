package app.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class ControllerAutenticacao {
    public static void middlewareAutenticacao(Request req, Response res) {
        String token = req.headers("token");
        try {  
            JWTVerifier verificador = JWT.require(Algorithm.HMAC256("valter")).build();
            verificador.verify(token);
        } catch (JWTVerificationException exception) {
            halt(401, "Usuário não autenticado. Razão: " + exception.getMessage());
        }
    }
}
