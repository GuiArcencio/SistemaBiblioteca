package app.Domain.PacoteObras;

import app.Domain.PacoteObras.Estados.*;
import app.Exception.AnnotatedDeserializer.JsonRequired;

public class Copia {
	private Long id;

	private State state;

    @JsonRequired
    private Long obraId;

    @JsonRequired
    private String tipoEstado;

    public Copia(Long id) {
        this.id = id;
    }
	
	public Copia(State state, Long obraId) {
		this.state = state;
        this.obraId = obraId;
        this.tipoEstado = state.getState();
	}

    public Copia(Long id, State state, Long obraId) {
        this(state, obraId);
        this.id = id;
        this.tipoEstado = state.getState();
    }

    public Copia(State state){
        this.state = state;
    }

    public Copia(Long obraId, String tipoEstado){
        this.obraId = obraId;
        if(tipoEstado == "Disponivel"){
            this.state = Disponivel.getInstancia();
        }
        else if(tipoEstado == "Emprestado") {
            this.state = Emprestado.getInstancia();
        }
        else if(tipoEstado == "Reservado") {
            this.state = Reservado.getInstancia();
        }
        this.tipoEstado = state.getState();
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void setObraId(Long obraID){
        this.obraId = obraID;
    }

    public Long getObraId() {
        return this.obraId;
    }
	
	public void emprestar() {
        
	}
	
	public void devolver() {
		
	}
	
	public void reservar() {
		
	}
	
	public State estadoAtual() {
		return this.state;
	}

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    

    
}
