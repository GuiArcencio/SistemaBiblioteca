package app.Controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Editora;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.EditoraService;
import app.Service.spec.IEditoraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerEditora {
    
    private static IEditoraService service = new EditoraService();

    private static Gson gsonEditora() {
        return new GsonBuilder()
        .registerTypeAdapter(Editora.class, new AnnotatedDeserializer<Editora>())
        .create();
    }

    /*
     * Retorna todas as editoras existentes
     */
    public static Route getEditoras = (Request req, Response res) -> {
        res.type("application/json");
        List<Editora> editoras = service.buscaEditoras();
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editoras));
    };

    /*
     * Retorna uma editora específica através do id
     */
    public static Route getEditora = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id")); 
        Editora editora = service.buscaEditoraPorId(id);
        if(editora != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editora));
        }else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca da editora");
        }
    };

    /*
     * Cria uma nova editora
     */
    public static Route criarEditora = (Request req, Response res) -> {
        res.type("application/json"); 
        Gson gson = gsonEditora();
        Editora editora = gson.fromJson(req.body(), Editora.class);
        if (service.insereEditora(editora)) {
            System.out.println("Endereço novo inserido:");
            System.out.println(new Gson().toJsonTree(editora));
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção do endereço");
        }
    };

    /*
     * Remove uma editora
     */
    public static Route removerEditora = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        System.out.println("Removendo editora de ID: " + id);
        if (service.removeEditora(id)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção da editora, o ID é válido?");
        }
    };

    /*
     * Altera uma editora
     */
    public static Route alterarEditora = (Request req, Response res) -> {
        res.type("application/json");
        Editora editora = new Gson().fromJson(req.body(), Editora.class);
        Long id = Long.parseLong(req.params(":id"));
        if (service.alteraEditora(id, editora)) {
            return new StandardResponse(StatusResponse.SUCCESS);
        } else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na alteração da editora. o ID é válido?");
        }
    };
}
