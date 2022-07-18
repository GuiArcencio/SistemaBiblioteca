package obras;

class Reservado implements State {
	private static Reservado instancia;
	private Reservado() {
		Reservado.instancia = new Reservado();
	}
	
	public static Reservado getInstancia() {
		if (Reservado.instancia == null) {
			Reservado.instancia = new Reservado();
		}
		
		return Reservado.instancia;
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
	public State getState() {
		return this;
	}

}
