package app.Domain.PacoteEntradaSaidaObras;

import java.util.Date;
import app.Domain.SubjectObserver.Subject;
import app.Exception.AnnotatedDeserializer.JsonRequired;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Leitor;
//import app.Funcionario.Funcionario;

public class Reserva extends Subject {
    private Long id;

    @JsonRequired
    private Date dataReserva;

    @JsonRequired
    private Date dataPrevistaRetirada;

    @JsonRequired
    private Date dataPrevistaDevolucao;

    // @JsonRequired
    //private Funcionario funcionarioResponsavel

    @JsonRequired
    private Leitor leitor;

    @JsonRequired
    private Copia copiaReservada;

    public Reserva(Long id, Date dataReserva, Date dataPrevistaRetirada, Date dataPrevistaDevolucao, Leitor leitor, Copia copiaReservada) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.dataPrevistaRetirada = dataPrevistaRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.leitor = leitor;
        this.copiaReservada = copiaReservada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataReserva() {
        return this.dataReserva;
    }

    public void setDataPrevistaRetirada(Date dataPrevistaRetirada) {
        this.dataPrevistaRetirada = dataPrevistaRetirada;
    }

    public Date getDataPrevistaRetirada() {
        return this.dataPrevistaRetirada;
    }

    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Date getDataPrevistaDevolucao() {
        return this.dataPrevistaDevolucao;
    }

    public void setCopia(Copia copia) {
        this.copiaReservada = copia;
    }

    public Copia getCopia() {
        return this.copiaReservada;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Leitor getLeitor() {
        return this.leitor;
    }

}
