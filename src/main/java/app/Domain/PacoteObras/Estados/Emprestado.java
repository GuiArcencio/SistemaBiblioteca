package app.Domain.PacoteObras.Estados;


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
		//metodo utilizado para quando uma cópia emprestada é devolvida e já existe uma
		//reserva feita para a mesma cópia
        return Reservado.getInstancia();
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
