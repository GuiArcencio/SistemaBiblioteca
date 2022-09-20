package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteUsuarios.Endereco;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.EnderecoService;
import app.Service.spec.IEnderecoService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerEndereco {
    
    private static IEnderecoService service = new EnderecoService();

    private static Gson gsonEndereco() {
        return new GsonBuilder()
        .registerTypeAdapter(Endereco.class, new AnnotatedDeserializer<Endereco>())
        .create();
    }

    /*
     * Retorna todos os endereços existentes
     */
    public static Route getEnderecos = (Request req, Response res) -> {
        res.type("application/json");
        List<Endereco> enderecos = service.buscaEnderecos();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(enderecos));
    };

    /*
     * Retorna um endereço específico através do id
     */
    public static Route getEndereco = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id")); 
        Endereco endereco = service.buscaEnderecoPorId(id);
        if(endereco != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(endereco));
        }else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca do endereço");
        }
    };

    /*
     * Cria um novo endereço
     */
    public static Route criarEndereco = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonEndereco();
        Endereco endereco = gson.fromJson(req.body(), Endereco.class);
        if (service.insereEndereco(endereco)) {
            System.out.println("Endereço novo inserido:");
            System.out.println(new Gson().toJsonTree(endereco));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do endereço");
        }
    };

    /*
     * Remove um endereço
     */
    public static Route removerEndereco = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo endereço de ID: " + id);
        if (service.removeEndereco(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção do endereço, o ID é válido?");
        }
    };

    /*
     * Altera um endereço
     */
    public static Route alterarEndereco = (Request req, Response res) -> {
        res.type("application/json");
        Endereco endereco = new Gson().fromJson(req.body(), Endereco.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alteraEndereco(id, endereco)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração de endereco. o ID é válido?");
        }
    };
}
