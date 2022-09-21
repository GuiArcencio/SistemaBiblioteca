package app.Service.spec;

import java.util.List;

import app.Domain.PacoteObras.Copia;

public interface ICopiaService {
    List<Copia> buscaCopias(Long codigoObra);
    List<Copia> buscarDisponiveisByObraId(Long obraId);
    Copia buscaCopia(Long id);
    boolean mudarEstado(Long id, Copia copias);
    boolean insereCopia(Copia copia);
    boolean removerCopia(Long id);
    boolean alterarCopia(Long id, Copia Copia);
}
