package app.Domain.PacoteObras;

import app.Exception.AnnotatedDeserializer.JsonRequired;

public class RelObraAutor {
    
    private Long id;

    @JsonRequired
    private Autor autor;

    @JsonRequired
    private Obra obra;

    

    public RelObraAutor(Long id, Autor autor, Obra obra) {
        this.id = id;
        this.autor = autor;
        this.obra = obra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }


}
