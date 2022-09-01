package app.Controllers;

import java.math.BigInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteUsuarios.Usuario;
import app.Exception.AnnotatedDeserializer;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Usuario;
import app.Service.impl.UsuarioService;
import app.Service.spec.IUsuarioService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerUsuario {

    private static IUsuarioService service = new UsuarioService();


    private static Gson gsonUsuario() {
        return new GsonBuilder()
        .registerTypeAdapter(Usuario.class, new AnnotatedDeserializer<Usuario>())
        .create();
    }

    private static Gson gsonLeitor() {
        return new GsonBuilder()
        .registerTypeAdapter(Leitor.class, new AnnotatedDeserializer<Leitor>())
        .create();
    }
    

    /*
     * Cria um Funcionario
     */
    public static Route criarFuncionario = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonUsuario();
        Funcionario funcionario = gson.fromJson(req.body(), Leitor.class);
        if(service.insereLeitor(leitor)){
            System.out.println("Novo Leitor inserido:");
            System.out.println(new Gson().toJsonTree(leitor));
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
        }else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do leitor");
        }
    };

    /*
     * Cria um Leitor
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonLeitor();
        Leitor leitor = gson.fromJson(req.body(), Leitor.class);
        if(service.insereLeitor(leitor)){
            System.out.println("Novo Leitor inserido:");
            System.out.println(new Gson().toJsonTree(leitor));
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
        }else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do leitor");
        }
    };

    /*
     * Cria um 
     */
    public static Route criarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonLeitor();
        Leitor leitor = gson.fromJson(req.body(), Leitor.class);
        lDAO.insert(leitor);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
    };

    /*
     * Remove um Leitor
     */
    public static Route removerAutor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = req.params(":id");
        Leitor leitor = new leitor(id);
        lDAO.delete(leitor);
        return new StandardResponse(StatusResponse.SUCCESS);
    };
    
}
