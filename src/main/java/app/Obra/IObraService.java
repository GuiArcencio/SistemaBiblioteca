package app.Obra;

import java.math.BigInteger;

public interface IObraService {
    Obra buscaObra(BigInteger isbn);
    void adicionarAutor(String autor);
    void removerAutor(int indice);
    void adicionarPalavraChave(String palavra);
    void removerPalavraChave(int indice);
    void adicionarCopia(int idCopia);
    void removerCopia(int indice);
    void marcarEmprestadoCopia(int idCopia);
    void marcarDevolverCopia(int idCopia);
    void marcarDisponivelCopia(int idCopia);
}
