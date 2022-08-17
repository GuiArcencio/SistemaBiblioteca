package main.java.br.ufscar.dc.pooa.domain;

public class Disponivel implements State {
	private static Disponivel instancia;
	private Disponivel() {
		Disponivel.instancia = new Disponivel();
	}
	
	public static Disponivel getInstancia() {
		if (Disponivel.instancia == null) {
			Disponivel.instancia = new Disponivel();
		}
		
		return Disponivel.instancia;
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
