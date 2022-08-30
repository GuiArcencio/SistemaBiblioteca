package app.Leitor;

import app.Usuario.Usuario;
import app.CategoriaLeitor.CategoriaLeitor;
//import app.Disciplina;

public class Leitor extends Usuario {
    private String email;
    private String documentoId;
    //private Disciplina disciplina;
    private boolean grupoAcademico;

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
}
