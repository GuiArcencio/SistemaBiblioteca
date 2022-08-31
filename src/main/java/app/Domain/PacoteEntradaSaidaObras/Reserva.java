package app.Domain.PacoteEntradaSaidaObras;

import java.util.Date;
import app.Domain.SubjectObserver.Subject;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Leitor;
//import app.Funcionario.Funcionario;

public class Reserva extends Subject {
    private Long id;
    private Date dataReserva;
    private Date dataPrevistaRetirada;
    private Date dataPrevistaDevolucao;
    //private Funcionario funcionarioResponsavel
    private Leitor leitor;
    private Copia copiaReservada;

    public Reserva(Long id, Date dataReserva, Date dataPrevistaRetirada, Date dataPrevistaDevolucao, Leitor leitor, Copia copiaReservada) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.dataPrevistaRetirada = dataPrevistaRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.leitor = leitor;
        this.copiaReservada = copiaReservada;
    }
}
