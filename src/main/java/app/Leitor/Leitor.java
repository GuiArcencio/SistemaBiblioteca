package app.Leitor;

import app.Usuario.Usuario;
import app.CategoriaLeitor.CategoriaLeitor;
//import app.Disciplina;


public class Leitor extends Usuario {
    private Long id;
    private String email;
    private String documentoId;
    //private Disciplina disciplina;
    private boolean grupoAcademico;
    private CategoriaLeitor categoria;

    public Leitor(Long id, String email, String documentoId, boolean grupoAcademico, CategoriaLeitor categoria){
        this.id = id;
        this.email = email;
        this.documentoId = documentoId;
        this.grupoAcademico = grupoAcademico;
        this.categoria = categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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
