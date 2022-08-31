package app.Domain.PacoteObras.Estados;

public abstract class State {
	public abstract State emprestar();
	public abstract State disponibilizar();
	public abstract State reservar();
	public abstract State cancelar();
	public abstract String getState();
}
