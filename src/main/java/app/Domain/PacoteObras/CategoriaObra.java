package app.Domain.PacoteObras;

import app.Exception.AnnotatedDeserializer.JsonRequired;

import java.time.LocalDate;

public class CategoriaObra {
	private Long codigo;

    @JsonRequired
	private String descricao;

    @JsonRequired
	private int maximoDiasEmprestimo;

    @JsonRequired
	private double taxaMulta;
	
	public CategoriaObra(Long codigo, String descricao, int maximoDiasEmprestimo, double taxaMulta) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.maximoDiasEmprestimo = maximoDiasEmprestimo;
		this.taxaMulta = taxaMulta;
	}

    public CategoriaObra(){}
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
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
