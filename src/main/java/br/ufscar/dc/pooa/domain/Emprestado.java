package main.java.br.ufscar.dc.pooa.domain;

class Emprestado implements State {
	private static Emprestado instancia;
	private Emprestado() {
		Emprestado.instancia = new Emprestado();
	}
	
	public static Emprestado getInstancia() {
		if (Emprestado.instancia == null) {
			Emprestado.instancia = new Emprestado();
		}
		
		return Emprestado.instancia;
	}

	@Override
	public void emprestar() {

	}

	@Override
	public void disponibilizar() {
		
	}

	@Override
	public void reservar() {

	}

	@Override
	public void cancelar() {

	}

	@Override
	public State getState() {
		return this;
	}

}
