package app.Service.spec;


import java.util.List;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;

public interface IObraService {
    List<Obra> buscaObra(Long isbn);
    Obra buscaObraByCodigo(Long codigo);
    Obra buscaObra(String titulo);
    boolean adicionarObra(Obra obra);
    boolean removerObra(Obra obra);
    
    List<Autor> buscarAutores();
    Autor buscarAutor(Long id);
    boolean adicionarAutor(Long codigo, Autor autor);
    boolean removerAutor(Long codigo, Autor autor);

    List<Obra> buscarObrasPPC(String palavra);
    boolean adicionarPalavraChave(Long codigo, String palavra);
    boolean removerPalavraChave(Long codigo, String palavra);

    List<Copia> buscarCopias(Long codigo);
    Copia buscarCopia(Long idCopia);
    boolean adicionarCopia(Long codigo, Copia copia);
    boolean removerCopia(Copia copia);

}
