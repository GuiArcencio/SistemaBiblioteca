package app.Service.spec;

import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;

public interface IDevolucaoService {
    List<Devolucao> buscaDevolucoes(Long idUsuario);
    Devolucao buscaDevolucao(Long id);
    boolean realizarDevolucao(Devolucao Devolucao);
    boolean alterarDevolucao(Long id, Devolucao Devolucao);
    boolean removerDevolucao(Long id);
}
