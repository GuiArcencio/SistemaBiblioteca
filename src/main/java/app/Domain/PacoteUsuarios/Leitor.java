package app.Domain.PacoteUsuarios;


import app.Domain.PacoteUsuarios.CategoriaLeitor;
//import app.Disciplina;
import app.Exception.AnnotatedDeserializer.JsonRequired;


public class Leitor extends Usuario {
    @JsonRequired
    private String email;

    @JsonRequired
    private String documentoId;

    // @JsonRequired
    //private Disciplina disciplina;

    @JsonRequired
    private boolean grupoAcademico;

    @JsonRequired
    private CategoriaLeitor categoria;

    public Leitor(Long id){
        this.setId(id);
    }

    public Leitor(Long id, String email, String documentoId, boolean grupoAcademico, CategoriaLeitor categoria){
        this.id = id;
        this.email = email;
        this.documentoId = documentoId;
        this.grupoAcademico = grupoAcademico;
        this.categoria = categoria;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public String getDocumentoId() {
        return this.documentoId;
    }

    /*
    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    */

    public boolean getGrupoAcademico() {
        return this.grupoAcademico;
    }

    public void setGrupoAcademico(boolean grupoAcademico) {
        this.grupoAcademico = grupoAcademico;
    }

    
    public void setCategoria(CategoriaLeitor categoria) {
        this.categoria = categoria;
    }

    public CategoriaLeitor getCategoria() {
        return this.categoria;
    }
}
