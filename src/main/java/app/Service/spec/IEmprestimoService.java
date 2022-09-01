package app.Service.spec;

import java.math.BigInteger;
import java.util.List;
import java.util.Date;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteUsuarios.Funcionario;

public interface IEmprestimoService {
    public List<Emprestimo> buscaEmprestimos(Long idUsuario);
    public List<Devolucao> buscaDevolucoes(Long idUsuario);
    public Copia emprestarObra(Long idUsuario, Long isbn, Funcionario funcionarioResponsavel);
    public Copia devolverObra(Long idUsuario, Long isbn);
    public Copia reservarObra(Long idUsuario, Long isbn, Date dataRetirada);
}
