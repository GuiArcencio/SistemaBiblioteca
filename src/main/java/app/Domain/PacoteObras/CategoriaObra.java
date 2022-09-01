package app.Domain.PacoteObras;

import app.Exception.AnnotatedDeserializer.JsonRequired;

import java.time.LocalDate;

public class CategoriaObra {
	int codigo;

    @JsonRequired
	String descricao;

    @JsonRequired
	int maximoDiasEmprestimo;

    @JsonRequired
	double taxaMulta;
	
	public CategoriaObra(int codigo, String descricao, int maximoDiasEmprestimo, double taxaMulta) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.maximoDiasEmprestimo = maximoDiasEmprestimo;
		this.taxaMulta = taxaMulta;
	}
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setMaximoDiasEmprestimo(int dias) {
        this.maximoDiasEmprestimo = dias;
    }

    public int getMaximoDiasEmprestimo() {
        return this.maximoDiasEmprestimo;
    }

    public void setTaxaMulta(double taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public double getTaxaMulta() {
        return this.taxaMulta;
    }

    public LocalDate calculaDataDevolucao(){
        LocalDate dataAtual = LocalDate.now();
        dataAtual = dataAtual.plusDays(maximoDiasEmprestimo);
        return dataAtual;
    }
}
