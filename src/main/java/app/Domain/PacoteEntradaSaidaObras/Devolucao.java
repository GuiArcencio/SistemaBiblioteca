package app.Domain.PacoteEntradaSaidaObras;

import java.util.Date;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;

public class Devolucao {
    private Long id;
    private Date dataDevolucao;
    private double multaTotal;
    private Emprestimo emprestimoCorrespondente;

    public Devolucao(Long id, Date dataDevolucao, double multaTotal, Emprestimo emprestimoCorrespondente) {
        this.id = id;
        this.dataDevolucao = dataDevolucao;
        this.multaTotal = multaTotal;
        this.emprestimoCorrespondente = emprestimoCorrespondente;
    }
}
