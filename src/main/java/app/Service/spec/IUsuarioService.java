package app.Service.spec;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Funcionario;
//import app.Domain.PacoteUsuarios.Usuario;

public interface IUsuarioService {

    boolean insereLeitor(Leitor leitor);
    boolean insereFuncionario(Funcionario funcionario);

    boolean removeLeitor(Long id);
    boolean removeFuncionario(Long id);

    boolean alteraLeitor(Long id, Leitor leitor);
    boolean alteraFuncionario(Long id, Funcionario funcionario);

    /*
    boolean uinsereLeitor(Usuario leitor);
    boolean uinsereFuncionario(Usuario funcionario);

    boolean ualteraLeitor(Long id, Usuario leitor);
    boolean ualteraFuncionario(Long id, Usuario funcionario);
    */
    
}
