package app.Service.spec;

import java.util.List;

import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;

public interface IFuncionarioService {
    
    List<Funcionario> getFuncionarios();
    Funcionario getFuncionario(Long id);

    boolean insereFuncionario(Funcionario funcionario);
    boolean removeFuncionario(Long id);
    boolean alteraFuncionario(Long id, Funcionario funcionario);

    boolean alterarEmprestimo(Leitor leitor, Long copiaId);
    boolean alterarDevolucao(Leitor leitor, Long copiaId);
    boolean alterarReserva(Leitor leitor, Long copiaId);

}   
