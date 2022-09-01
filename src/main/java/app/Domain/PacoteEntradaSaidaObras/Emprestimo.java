package app.Domain.PacoteEntradaSaidaObras;

import java.util.Date;
import app.Domain.SubjectObserver.Subject;
import app.Exception.AnnotatedDeserializer.JsonRequired;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Funcionario;

public class Emprestimo extends Subject{
    private Long id;

    @JsonRequired
    private Date dataEmprestimo;

    @JsonRequired
    private Date dataPrevistaDevolucao;

    @JsonRequired
    private Funcionario funcionarioResponsavel;

    @JsonRequired
    private Copia copia;

    @JsonRequired
    private Leitor leitor;

    @JsonRequired
    private boolean atrasado;

    public Emprestimo(Long id){
        this.id = id;
    }
    
    public Emprestimo(Long id, Date dataEmprestimo, Date dataPrevistaDevolucao, Funcionario funcionarioResponsavel, Copia copia, Leitor leitor, boolean atrasado) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo; 
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.funcionarioResponsavel = funcionarioResponsavel;
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

    public void setFuncionarioResponsavel(Funcionario fr) {
        this.funcionarioResponsavel = fr;
    }

    public Funcionario getFuncionarioResponsavel() {
        return this.funcionarioResponsavel;
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
        if (atrasado = true){
            this.notifyAllObservers();
        }
    }

    public boolean getAtrasado() {
        return this.atrasado;
    }
}
