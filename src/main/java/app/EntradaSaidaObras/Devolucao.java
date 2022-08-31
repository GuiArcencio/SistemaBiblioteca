package app.EntradaSaidaObras;

import java.util.Date;
import app.EntradaSaidaObras.Emprestimo;

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
