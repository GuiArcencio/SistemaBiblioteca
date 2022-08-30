package app.StandardResponse;

public enum StatusResponse {
    SUCCESS ("Success"),
    ERROR ("Error");
 
    private String status;       
    
    StatusResponse(String status){
        this.status = status;
    }

    public String getDescricao() {
        return status;
    }
}
