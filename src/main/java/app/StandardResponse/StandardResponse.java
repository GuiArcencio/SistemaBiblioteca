package app.StandardResponse;
import com.google.gson.JsonElement;

/*
 * Status indica se a requisição foi um sucesso ou fracasso
 * A mensagem é usada no caso de fracasso
 * Os dados são enviados para as requisições que precisem
 */
public class StandardResponse {
    
    private StatusResponse status;
    private String message;
    private JsonElement data;

    // Construtores

    public StandardResponse(StatusResponse status){
        this.status = status;
    }

    public StandardResponse(StatusResponse status, String message){
        this.status = status;
        this.message = message;
    }

    public StandardResponse(StatusResponse status, JsonElement data){
        this.status = status;
        this.data = data;
    }
 
    // Getters e Setters

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
