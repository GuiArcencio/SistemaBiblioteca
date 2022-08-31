package app.Service.spec;

import java.math.BigInteger;
import java.util.List;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;

public interface IObraService {
    Obra buscaObra(BigInteger isbn);
    void adicionarObra(Obra obra);
    void removerObra(Obra obra);
    
    List<Autor> buscarAutores();
    void adicionarAutor(BigInteger isbn, Autor autor);
    void removerAutor(BigInteger isbn, Autor autor);

    List<String> buscarPalavrasChave();
    void adicionarPalavraChave(BigInteger isbn, String palavra);
    void removerPalavraChave(BigInteger isbn, String palavra);

    Copia buscarCopias(BigInteger isbn);
    Copia buscarCopia(int idCopia);
    void adicionarCopia(BigInteger isbn, Copia copia);
    void removerCopia(BigInteger isbn, Copia copia);

    void marcarEmprestadoCopia(int idCopia);
    void marcarDevolverCopia(int idCopia);
    void marcarDisponivelCopia(int idCopia);
}
