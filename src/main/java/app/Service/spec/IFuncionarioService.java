package app.Service.spec;

import app.Domain.PacoteUsuarios.Leitor;

public interface IFuncionarioService {
    
    boolean devolverObraLeitor(Leitor leitor, Long obraId);
    boolean reservarObraLeitor(Leitor leitor, Long obraId);
    boolean emprestarObraLeitor(Leitor leitor, Long obraId);
    boolean alterarEmprestimo(Leitor leitor, Long obraId);
    boolean alterarDevolucao(Leitor leitor, Long obraId);
    boolean alterarReserva(Leitor leitor, Long obraId);

}   
