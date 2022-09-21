package app.Service.spec;

import java.util.List;

//import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;

public interface IObraService {
    List<Obra> buscaObras();
    List<Obra> buscaObraPorIsbn(Long isbn);
    Obra buscaObraPorCodigo(Long codigo);
    Obra buscaObraPorTitulo(String titulo);
    boolean adicionaObra(Obra obra);
    boolean removeObra(Obra obra);
    
    //List<Autor> buscarAutores();
    //Autor buscarAutor(Long id);
    //boolean adicionarAutor(Long codigo, Autor autor);
    //boolean removerAutor(Long codigo, Autor autor);

    List<Obra> buscarObrasPPC(String palavra);
    boolean adicionarPalavraChave(Long codigo, String palavra);
    boolean removerPalavraChave(Long codigo, String palavra);

    List<Copia> buscarCopias(Long codigo);
    Copia buscarCopia(Long idCopia);
    boolean adicionarCopia(Long codigo, Copia copia);
    boolean removerCopia(Copia copia);

}
