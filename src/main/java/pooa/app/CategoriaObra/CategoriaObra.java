package main.java.pooa.app.CategoriaObra;

public class CategoriaObra {
	int codigo;
	String descricao;
	int maximoDiasEmprestimo;
	double taxaMulta;
	
	public CategoriaObra(int codigo, String descricao, int maximoDiasEmprestimo, double taxaMulta) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.maximoDiasEmprestimo = maximoDiasEmprestimo;
		this.taxaMulta = taxaMulta;
	}
}
