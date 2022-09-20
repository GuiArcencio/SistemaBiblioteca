package app.Domain.PacoteObras;

import app.Exception.AnnotatedDeserializer.JsonRequired;

public class Editora {
    private Long id;

    @JsonRequired
    private String nome;

    public Editora(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Editora() {
    }

    public void setId(Long id)  {
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
   
}
