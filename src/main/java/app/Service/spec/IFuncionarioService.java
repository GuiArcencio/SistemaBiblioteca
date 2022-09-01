package app.Service.spec;

import app.Domain.PacoteUsuarios.Leitor;

public interface IFuncionarioService {
    
    boolean alterarEmprestimo(Leitor leitor, Long copiaId);
    boolean alterarDevolucao(Leitor leitor, Long copiaId);
    boolean alterarReserva(Leitor leitor, Long copiaId);

}   
