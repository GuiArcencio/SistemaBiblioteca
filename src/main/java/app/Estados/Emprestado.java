package app.Estados;

import app.Estados.State;

public class Emprestado extends State {

    private static Emprestado instancia;

	private Emprestado() {
	}
	

	public static State getInstancia() {
		if (Emprestado.instancia == null) {
			Emprestado.instancia = new Emprestado();
		}
		
		return Emprestado.instancia;
	}

	@Override
	public State emprestar() {
        System.out.println("já está emprestado");
        return this;
	}

	@Override
	public State disponibilizar() {
		return Disponivel.getInstancia();
	}

	@Override
	public State reservar() {
        System.out.println("obra emprestada, não é possível reservar");
        return this;
	}

	@Override
	public State cancelar() {
        System.out.println("obra não está reservada");
        return this;
	}

	@Override
	public String getState() {
		return "Emprestado";
	}

}
