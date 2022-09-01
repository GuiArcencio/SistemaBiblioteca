package app.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteUsuarios.Usuario;
import app.Exception.AnnotatedDeserializer;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Service.impl.UsuarioService;
import app.Service.spec.IUsuarioService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerUsuario {

    private static IUsuarioService service = new UsuarioService();


    private static Gson gsonFuncionario() {
        return new GsonBuilder()
        .registerTypeAdapter(Funcionario.class, new AnnotatedDeserializer<Usuario>())
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
        Gson gson = gsonFuncionario();
        Funcionario funcionario = gson.fromJson(req.body(), Funcionario.class);
        if(service.insereFuncionario(funcionario)){
            System.out.println("Novo Funcionario inserido:");
            System.out.println(new Gson().toJsonTree(funcionario));
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(funcionario));
        }else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do funcionario");
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
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(leitor));
        }else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do leitor");
        }
    };


    /*
     * Remove um Funcionario
     */
    public static Route removerFuncionario = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo Funcionario de ID: " + id);
        if (service.removeFuncionario(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção do funcionário, o ID é válido?");
        }
    };
     /*
     * Remove um Leitor
     */
    public static Route removerLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo Leitor de ID: " + id);
        if (service.removeLeitor(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção do leitor, o ID é válido?");
        }
    };


    /*
     * Altera um Funcionario
     */
    public static Route alterarFuncionario = (Request req, Response res) -> {
        res.type("application/json");
        Funcionario funcionario = new Gson().fromJson(req.body(), Funcionario.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alteraFuncionario(id, funcionario)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração do funcionário. o ID é válido?");
        }
    };
    /*
     * Altera um Leitor
     */
    public static Route alterarLeitor = (Request req, Response res) -> {
        res.type("application/json");
        Leitor leitor = new Gson().fromJson(req.body(), Leitor.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alteraLeitor(id, leitor)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração do leitor. o ID é válido?");
        }
    };
    
}
