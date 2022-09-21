package app.Domain.PacoteUsuarios;

import app.Exception.AnnotatedDeserializer.JsonRequired;
import java.util.Observable;
import java.util.Observer;

public class PessoaInteressada implements Observer{
    private Long id;

    private String status;

    @JsonRequired
    private Long leitorId;

    @JsonRequired
    private Long obraCodigo;

    public PessoaInteressada(){}

    public PessoaInteressada(Long id, Long li, Long oc){
        this.id = id;
        this.leitorId = li;
        this.obraCodigo = oc;
    }

    @Override
    public void update(Observable obra, Object status) {
        this.status = (String)status;
    }

    //Getters e Stters//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(Long leitorId) {
        this.leitorId = leitorId;
    }

    public Long getObraCodigo() {
        return obraCodigo;
    }

    public void setObraCodigo(Long obraCodigo) {
        this.obraCodigo = obraCodigo;
    }
    
}
