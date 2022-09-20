package app.Service.spec;

import java.util.List;

import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.RelObraAutor;
import app.Domain.PacoteObras.Autor;

public interface IRelObraAutorService {
    List<Obra> buscaObrasPorAutor(Long codigo_autor);
    List<Autor> buscaAutoresPorObra(Long codigo_obra);
    RelObraAutor buscaROAPorId(Long id);
    boolean adicionaROA(RelObraAutor roa);
    boolean removeROA(Long id);
    boolean atualizaROA(Long id, RelObraAutor roa);
    
}
