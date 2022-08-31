package app.Domain.PacoteObras;

public class Editora {
    private Long id;
    private String nome;

    public Editora(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
