package app.Controllers;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteUsuarios.Usuario;
import app.Exception.AnnotatedDeserializer;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Service.impl.FuncionarioService;
import app.Service.spec.IFuncionarioService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerFuncionario {

    private static IFuncionarioService service = new FuncionarioService();

    private static Gson gsonFuncionario() {
        return new GsonBuilder()
        .registerTypeAdapter(Funcionario.class, new AnnotatedDeserializer<Usuario>())
        .create();
    }

    /*
     * Busca Funcionarios
     */
    public static Route buscaFuncionarios = (Request req, Response res) -> {
        res.type("application/json");
        List<Funcionario> funcionarios = service.getFuncionarios();
        if (funcionarios != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(funcionarios));
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro ao obter funcionários");
        }
    };

    /*
     * Busca um Funcionario
     */
    // TODO: Implementar
    public static Route buscaFuncionario = (Request req, Response res) -> {
        res.type("application/json");
        res.status(501);
        return new StandardResponse(StatusResponse.ERROR, "Implementar");
    };

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

}
