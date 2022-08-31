package app.Copia;
import app.Estados.*;

public class Copia {
	private int id;
	private State state;
    private Long obraId;
	
	public Copia(int id, State state) {
		this.id = id;
		this.state = state;
	}

    public Copia(int id, State state, Long obraId) {
        this(id, state);
        this.obraId = obraId;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void setObraId(Long obraID){
        this.obraId = obraId;
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

    
}
