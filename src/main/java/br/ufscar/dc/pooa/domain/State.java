package obras;

interface State {
	public void emprestar();
	public void disponibilizar();
	public void reservar();
	public void cancelar();
	public State getState();
}
