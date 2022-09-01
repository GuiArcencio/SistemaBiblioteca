package app.Service.spec;

import java.util.List;
import java.util.Date;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Funcionario;

public interface IEmprestimoService {
    List<Emprestimo> buscaEmprestimos(Long idUsuario);
    List<Devolucao> buscaDevolucoes(Long idUsuario);
    Copia emprestarObra(Long idUsuario, Long isbn, Funcionario funcionarioResponsavel);
    Copia devolverObra(Long idUsuario, Long isbn);
    Copia reservarObra(Long idUsuario, Long isbn, Date dataRetirada, Funcionario funcionarioResponsavel);
}
