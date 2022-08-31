package app.Domain.PacoteObras.Estados;


public class Reservado extends State {

	private static Reservado instancia;

	private Reservado() {
	}
	
    
	public static State getInstancia() {
		if (Reservado.instancia == null) {
			Reservado.instancia = new Reservado();
		}
		
		return Reservado.instancia;
	}

	@Override
	public State emprestar() {
	    return Emprestado.getInstancia();
	}

	@Override
	public State disponibilizar() { 
        return Disponivel.getInstancia();
	}

	@Override
	public State reservar() {
        System.out.println("já está reservada");
        return this;
	}

	@Override
	public State cancelar() {
        return Disponivel.getInstancia();
	}


	@Override
	public String getState() {
		return "Reservado";
	}

}
