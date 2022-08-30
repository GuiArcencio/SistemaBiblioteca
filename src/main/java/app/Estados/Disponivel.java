package app.Estados;

import app.Estados.State;

public class Disponivel extends State {

	private static Disponivel instancia;

	private Disponivel() {
	}
	
    
	public static Disponivel getInstancia() {
		if (Disponivel.instancia == null) {
			Disponivel.instancia = new Disponivel();
		}
		
		return Disponivel.instancia;
	}

	@Override
	public State emprestar() {
		return Emprestado.getInstancia();
	}


	@Override
	public State disponibilizar() {
        System.out.println("já está disponível");
        return this;
	}

	@Override
	public State reservar() {
        return Reservado.getInstancia();
	}

	@Override
	public State cancelar() {
        System.out.println("não há o que cancelar");
        return this;
	}

	@Override
	public State getState() {
		return this;
	}

}
