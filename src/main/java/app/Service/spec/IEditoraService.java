package app.Service.spec;

import java.util.List;

import app.Domain.PacoteObras.Editora;

public interface IEditoraService {
    
    List<Editora> buscaEditoras();
    boolean insereEditora(Editora e);
    boolean removeEditora (Long id);
    boolean alteraEditora(Long id, Editora e);
    Editora buscaEditoraPorId(Long id);
}