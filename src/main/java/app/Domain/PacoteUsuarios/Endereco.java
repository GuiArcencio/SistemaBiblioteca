package app.Domain.PacoteUsuarios;

import app.Exception.AnnotatedDeserializer.JsonRequired;

public class Endereco {
    private Long id;

    @JsonRequired
    private String logradouro;

    @JsonRequired
    private int numero;

    @JsonRequired
    private int cep;

    @JsonRequired
    private String cidade;

    @JsonRequired
    private String estado;

    public Endereco(Long id, String logradouro, int numero, int cep, String cidade, String estado) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getCep() {
        return this.cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
