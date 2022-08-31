package app.Domain.PacoteEntradaSaidaObras;

import java.util.Date;
import app.Domain.SubjectObserver.Subject;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Leitor;
//import app.Funcionario.Funcionario

public class Emprestimo extends Subject{
    private Long id;
    private Date dataEmprestimo;
    private Date dataPrevistaDevolucao;
    //private Funcionario funcionarioResponsavel;
    private Copia copia;
    private Leitor leitor;
    private boolean atrasado;
    
    public Emprestimo(Long id, Date dataEmprestimo, Date dataPrevistaDevolucao, Copia copia, Leitor leitor, boolean atrasado) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo; 
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.copia = copia;
        this.leitor = leitor;
        this.atrasado = atrasado;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Date getDataPrevistaDevolucao() {
        return this.dataPrevistaDevolucao;
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }

    public Copia getCopia() {
        return this.copia;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Leitor getLeitor() {
        return this.leitor;
    }

    public void setAtrasado(boolean atrasado) {
        this.atrasado = atrasado;
    }

    public boolean getAtrasado() {
        return this.atrasado;
    }
}
