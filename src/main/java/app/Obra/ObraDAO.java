package app.Obra;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.Autor.Autor;
import app.CategoriaObra.CategoriaObra;

public class ObraDAO {
    Obra buscaObra(int isbn){
        // TODO: Substituir todo esse método pelo acesso ao BD
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

    void adicionarAutor(Autor autor){
        
    }

    void removerAutor(int indice){

    }

    void adicionarPalavraChave(String palavra){

    }

    void removerPalavraChave(int indice){

    }

    void adicionarCopia(int idCopia){

    }

    void removerCopia(int indice){

    }

    void marcarEmprestadoCopia(int idCopia){

    }

    void marcarDevolverCopia(int idCopia){

    }

    void marcarDisponivelCopia(int idCopia){

    }
}
