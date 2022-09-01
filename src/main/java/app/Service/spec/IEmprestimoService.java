package app.Service.spec;

import java.math.BigInteger;
import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Copia;

public interface IEmprestimoService {
    List<Emprestimo> buscaEmprestimos(BigInteger idUsuario);
    List<Devolucao> buscaDevolucoes(BigInteger idUsuario);
    Copia emprestarObra(BigInteger idUsuario, BigInteger isbn);
    Copia devolverObra(BigInteger idUsuario, BigInteger isbn);
    Copia reservarObra(BigInteger idUsuario, BigInteger isbn);
}
