package main.java.pooa.app.Estados;

interface State {
	public void emprestar();
	public void disponibilizar();
	public void reservar();
	public void cancelar();
	public State getState();
}
