package app.Service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.Editora;
import app.Service.spec.IObraService;
import app.dao.ObraDAO;

public class ObraService implements IObraService{
    private ObraDAO dao;

    public ObraService() {
        this.dao = new ObraDAO();
    }

    // TODO: Função de teste enquanto dao não está pronto
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
                1L,
                9780345391827L,
                new CategoriaObra(1, "Ficção Científica", 30, 1.4),
                autores,
                palavras_chave,
                dateFormat.parse("12-1995"),
                "Later Printing",
                new Editora(1L, "Editora"),
                "The Hitchhiker's Guide to the Galaxy",
                240
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void adicionarObra(Obra obra) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Autor> buscarAutores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void adicionarAutor(BigInteger isbn, Autor autor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removerAutor(BigInteger isbn, Autor autor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> buscarPalavrasChave() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void adicionarPalavraChave(BigInteger isbn, String palavra) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removerPalavraChave(BigInteger isbn, String palavra) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Copia buscarCopias(BigInteger isbn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia buscarCopia(int idCopia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void adicionarCopia(BigInteger isbn, Copia copia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removerCopia(BigInteger isbn, Copia copia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void marcarEmprestadoCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void marcarDevolverCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void marcarDisponivelCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removerObra(Obra obra) {
        // TODO Auto-generated method stub
        
    }

    // TODO: Potencialmente nessas funções de adicionar e tudo mais, colocar um retorno para indicar para o usuário
    // Se por exemplo, ele está tentando inserir uma obra que já existe

    // TODO: Substituir métodos pelo uso do dao
    
}
