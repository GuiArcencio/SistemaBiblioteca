package app.Obra;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.Autor.Autor;
import app.CategoriaObra.CategoriaObra;

public class ObraService implements IObraService{
    private ObraDAO dao;

    public ObraService() {
        this.dao = new ObraDAO();
    }

    // TODO: Potencialmente nessas funções de adicionar e tudo mais, colocar um retorno para indicar para o usuário
    // Se por exemplo, ele está tentando inserir uma obra que já existe

    // TODO: Substituir métodos pelo uso do dao
    @Override
    public Obra buscaObra(BigInteger isbn) {
        List<Autor> autores = new ArrayList<Autor>();
        autores.add(new Autor("Douglas Adams", "D.A"));

        List<String> palavras_chave = new ArrayList<String>();
        palavras_chave.add("hitchiker");
        palavras_chave.add("42");
        palavras_chave.add("universe");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");

        try {
            return new Obra(
                1,
                new BigInteger("9780345391827"),
                new CategoriaObra(1, "Ficção Científica", 30, 1.4),
                autores,
                palavras_chave,
                dateFormat.parse("12-1995"),
                "Later Printing",
                "Del Rey",
                240
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void adicionarAutor(String autor) {
        
    }

    @Override
    public void removerAutor(int indice) {
        
    }

    @Override
    public void adicionarPalavraChave(String palavra) {
        
    }

    @Override
    public void removerPalavraChave(int indice) {
        
    }

    @Override
    public void adicionarCopia(int idCopia) {
        
    }

    @Override
    public void removerCopia(int indice) {
        
    }

    @Override
    public void marcarEmprestadoCopia(int idCopia) {
        
    }

    @Override
    public void marcarDevolverCopia(int idCopia) {
        
    }

    @Override
    public void marcarDisponivelCopia(int idCopia) {
        
    }
}
