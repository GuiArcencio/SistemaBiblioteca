package app.Endereco;

public class Endereco {
    private String logradouro;
    private int numero;
    private int cep;
    private String cidade;
    private String estado;

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setNumero() {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getCep() {
        return this.numero;
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
