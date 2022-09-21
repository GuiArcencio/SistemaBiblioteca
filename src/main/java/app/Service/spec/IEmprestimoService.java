package app.Service.spec;

import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Emprestimo;

public interface IEmprestimoService {
    List<Emprestimo> buscaEmprestimos(Long idUsuario);
    Emprestimo buscaEmprestimo(Long id);
    boolean realizarEmprestimo(Emprestimo emprestimo);
    boolean alterarEmprestimo(Long id, Emprestimo emprestimo);
    boolean removerEmprestimo(Long id);
}
