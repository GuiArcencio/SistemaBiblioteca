package app.Service.spec;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Funcionario;

public interface IUsuarioService {

    boolean insereLeitor(Leitor leitor);
    boolean insereFuncionario(Funcionario funcionario);

    boolean removeLeitor(Long id);
    boolean removeFuncionario(Long id);

    boolean alteraLeitor(Long id, Leitor leitor);
    boolean alteraFuncionario(Long id, Funcionario funcionario);
    
}
