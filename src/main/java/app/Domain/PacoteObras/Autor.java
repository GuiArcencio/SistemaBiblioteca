package app.Domain.PacoteObras;

import app.Exception.AnnotatedDeserializer.JsonRequired;

public class Autor {
    private Long id;

    @JsonRequired
	private String nome;

    @JsonRequired
	private String iniciais;
	
	public Autor(String nome, String iniciais) {
		this.nome = nome;
		this.iniciais = iniciais;
	}

    public Autor(Long id, String nome, String iniciais) {
        this(nome, iniciais);
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }

    public String getIniciais() {
        return this.iniciais;
    }
}
