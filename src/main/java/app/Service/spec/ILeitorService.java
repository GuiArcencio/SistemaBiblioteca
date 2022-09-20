package app.Service.spec;

import java.util.List;

import app.Domain.PacoteUsuarios.Leitor;

public interface ILeitorService {
    List<Leitor> getLeitores();
    Leitor getLeitor(Long id);

    boolean insereLeitor(Leitor leitor);
    boolean removeLeitor(Long id);
    boolean alteraLeitor(Long id, Leitor leitor);
}
