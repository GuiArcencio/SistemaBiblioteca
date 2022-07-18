package obras;

class Copia {
	int id;
	State state;
	
	public Copia(int id, State state) {
		this.id = id;
		this.state = state;
	}
	
	public void emprestar() {
		
	}
	
	public void devolver() {
		
	}
	
	public void reservar() {
		
	}
	
	public State estadoAtual() {
		return this.state.getState();
	}
}
