package main.java.br.ufscar.dc.pooa.domain;

interface State {
	public void emprestar();
	public void disponibilizar();
	public void reservar();
	public void cancelar();
	public State getState();
}
