package app.Controllers;


import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Exception.AnnotatedDeserializer;
import app.Service.impl.CopiaService;
import app.Service.impl.ObraService;
import app.Service.spec.ICopiaService;
import app.Service.spec.IObraService;
import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerObras {

    private static IObraService service = new ObraService();
    private static ICopiaService cservice = new CopiaService();

    private static Gson gsonObra() {
        return new GsonBuilder()
        .registerTypeAdapter(Obra.class, new AnnotatedDeserializer<Obra>())
        .create();
    }


    private static Gson gsonCopia(){
        return new GsonBuilder()
        .registerTypeAdapter(Copia.class, new AnnotatedDeserializer<Copia>())
        .create();
    }

    private static void atualizaStatus(Long codigo){
        List<Copia> disponiveis = cservice.buscarDisponiveisByObraId(codigo);
        Obra obra = service.buscaObraPorCodigo(codigo);
        if(disponiveis.isEmpty()){
            System.out.println("Atualizando status da obra para indisponivel...");
            obra.setStatus("INDISPONIVEL");
            service.atualizaObra(codigo, obra);
        }
        else{
            System.out.println("Atualizando status da obra para disponivel...");
            obra.setStatus("DISPONIVEL");
            service.atualizaObra(codigo, obra);
        }
    }


    /*
     * Busca uma obra por codigo
     */
    public static Route buscaObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        Obra obra = service.buscaObraPorCodigo(codigo);
        if(obra != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obra));
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca da obra.");
        }
    };

    /*
     * Busca todas as obras
     */
    public static Route buscaObras = (Request req, Response res) -> {
        res.type("application/json");
        List<Obra> obras = service.buscaObras();
        if(obras != null){
            return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(obras));
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na busca das obras.");
        }
    };

    /*
     * Adiciona uma obra
     */
    public static Route adicionarObra = (Request req, Response res) -> {
        res.type("application/json");
        Gson gson = gsonObra();
        Obra obra = gson.fromJson(req.body(), Obra.class);
        if(service.adicionaObra(obra)){
            System.out.println("Obra inserida com sucesso!");
            System.out.println(new Gson().toJsonTree(obra));
            return new StandardResponse(StatusResponse.SUCCESS);
        }
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção da obra");
        }
    };

    /*
     * Remove uma obra por codigo
     */
    public static Route removerObra = (Request req, Response res) -> {
        res.type("application/json");
        Long codigo = Long.parseLong(req.params(":codigo"));
        Obra obra = service.buscaObraPorCodigo(codigo);
        service.removeObra(obra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Adiciona uma palavra chave a uma obra
     */
    public static Route adicionarPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        service.adicionarPalavraChave(codigo, palavra);
        
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Remove uma palavra chave a uma obra
     */
    public static Route removerPalavraChave = (Request req, Response res) -> {
        res.type("application/json");
        String palavra = new Gson().fromJson(req.body(), String.class);
        Long codigo = Long.parseLong(req.params(":codigo"));
        service.removerPalavraChave(codigo, palavra);
        return new StandardResponse(StatusResponse.SUCCESS);
    };

    /*
     * Insere uma cópia de uma obra
     */
    public static Route adicionarCopiaDeObra = (Request req, Response res) -> {
        res.type("application/json");
        //Long codigo = Long.parseLong(req.params(":codigo"));
        Gson gson = gsonCopia();
        Copia copia = gson.fromJson(req.body(), Copia.class);

        //Uma nova cópia está sempre disponível
        //copia.setState(Disponivel.getInstancia());
        //copia.setObraId(copia.getId());
        if(cservice.insereCopia(copia)){
            System.out.println("Copia inserida com sucesso!");
            System.out.println(new Gson().toJsonTree(copia));
            atualizaStatus(copia.getObraId());
            return new StandardResponse(StatusResponse.SUCCESS);
        } 
        else{
            return new StandardResponse(StatusResponse.ERROR, "Erro na inserção de copia");
        }
    };

    /*
     * Remove uma cópia, com o id passado como parâmetro
     */
    public static Route removerCopiaDeObra = (Request req, Response res) -> {
        res.type("application/json");
        Long id = Long.parseLong(req.params(":id"));
        Long codigo = Long.parseLong(req.params(":codigo"));

        Copia c = cservice.buscaCopia(id);
        if (c.getObraId() == codigo && cservice.removerCopia(id)) {
            atualizaStatus(codigo);
            return new StandardResponse(StatusResponse.SUCCESS);
        }
        else {
            return new StandardResponse(StatusResponse.ERROR, "Erro na remoção de cópia");
        }
    };

    /*
     * Busca as cópias disponiveis de uma obra, com o codigo da obra como parâmetro
     */
    public static Route buscarCopiasDisponiveis = (Request req, Response res) -> {
        res.type("application/json");
        Long obraId = Long.parseLong(req.params(":codigo"));
        List<Copia> lista = cservice.buscarDisponiveisByObraId(obraId);
        return new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(lista));
    };
}
