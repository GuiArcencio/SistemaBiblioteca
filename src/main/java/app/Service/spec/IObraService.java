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
    boolean adicionarAutor(Long isbn, Autor autor);
    boolean removerAutor(Long isbn, Autor autor);

    List<Obra> buscarObrasPPC(String palavra);
    boolean adicionarPalavraChave(Long codigo, String palavra);
    boolean removerPalavraChave(Long codigo, String palavra);

    Copia buscarCopias(Long isbn);
    Copia buscarCopia(int idCopia);
    boolean adicionarCopia(Long isbn, Copia copia);
    boolean removerCopia(Long isbn, Copia copia);

    boolean marcarEmprestadoCopia(int idCopia);
    boolean marcarDevolverCopia(int idCopia);
    boolean marcarDisponivelCopia(int idCopia);
}
