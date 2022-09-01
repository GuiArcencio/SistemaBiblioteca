package app.Service.impl;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.Editora;
import app.Service.spec.IObraService;
import app.dao.ObraDAO;
import app.dao.AutorDAO;

public class ObraService implements IObraService{
    private ObraDAO odao;
    private AutorDAO adao;


    public ObraService() {
        this.odao = new ObraDAO();
        this.adao = new AutorDAO();
    }

    // TODO: Função de teste enquanto dao não está pronto
    @Override
    public List<Obra> buscaObra(Long isbn) {
       try{
        return odao.getByIsbn(isbn);
       } catch(Exception e){
        System.out.println("Obra(s) não encontrada! Retornando null.");
        return null;
       }
    }
    @Override
    public Obra buscaObra(String titulo) {
       try{
        return odao.getByTitulo(titulo);
       } catch(Exception e){
        System.out.println("Obra não encontrada! Retornando NULL.");
        return null;
       }
    }
    @Override
    public Obra buscaObraByCodigo(Long codigo) {
       try{
        return odao.getByCodigo(codigo);
       } catch(Exception e){
        System.out.println("Obra não encontrada! Retornando NULL.");
        return null;
       }
    }

    @Override
    public boolean adicionarObra(Obra obra) {
        try {
            odao.insert(obra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Autor> buscarAutores() {
        try {
            return adao.getAll();
        } catch (Exception e) {
            // Retorna lista vazia no caso de erro
            System.out.println("[ERRO] A busca de todos os autores falhou, retornando lista vazia");
            return new ArrayList<Autor>();
        }
    }

    @Override
    public Autor buscarAutor(Long id){
        try{
            return adao.getById(id);
        } catch(Exception e){
            System.out.println("[ERRO] Autor não encontrado! Retornando NULL");
            return null;
        }
    }

    @Override
    public boolean adicionarAutor(Long codigo, Autor autor) {
        try {
            Obra obra = buscaObraByCodigo(codigo);
            if(obra==null){
                System.out.println("[ERRO] Obra não contrada! Verifique o código informado. Se for uma nova Obra, adicione-a primeiro.");
                return false;
            }
            if(buscarAutor(autor.getId())==null){
                adao.insert(autor, obra);
            }
            obra.adicionarAutor(autor.getNome());
            return true;
        } catch (Exception e) {
            return false;
        }  
    }

    @Override
    public boolean removerAutor(Long codigo, Autor autor) {
        try {
            Obra obra = buscaObraByCodigo(codigo);
            if(obra==null){
                System.out.println("[ERRO] Obra não contrada! Verifique o código informado.");
                return false;
            }
            if(buscarAutor(autor.getId())==null){
                System.out.println("[ERRO] Autor não contrado! Verifique o id informado.");
                return false;
            }
            obra.removerAutor(autor);
            return true;
        } catch (Exception e) {
            return false;
        }  
        
    }

    @Override
    public List<Obra> buscarObrasPPC(String palavra) {
        try{
            return odao.getAllByKeyWord(palavra);
           } catch(Exception e){
            System.out.println("Nenhuma obra equivalente à palavra-chave informada foi encontrada! Retornando null.");
            return null;
           }
    }

    @Override
    public boolean adicionarPalavraChave(Long codigo, String palavra) {
        Obra obra = buscaObraByCodigo(codigo);
        if(obra == null){
            System.out.println("[ERRO] Obra não encontrada! Verifique o código informado.");
            return false;
        }
        List<String> palavrasChave = new ArrayList<>();
        palavrasChave = obra.getPalavrasChave();
        palavrasChave.add(palavra);
        obra.setPalavrasChave(palavrasChave);
        return true;
        
    }

    @Override
    public boolean removerPalavraChave(Long codigo, String palavra) {
        Obra obra = buscaObraByCodigo(codigo);
        if(obra == null){
            System.out.println("[ERRO] Obra não encontrada! Verifique o código informado.");
            return false;
        }
        List<String> palavrasChave = new ArrayList<>();
        palavrasChave = obra.getPalavrasChave();
        if(!palavrasChave.contains(palavra)){
            System.out.println("[ERRO] Palvra chave não encontrada! Verifique se não é erros de ortografia.");
            return false;
        }
        palavrasChave.remove(palavra);
        obra.setPalavrasChave(palavrasChave);
        return true;
        
    }

    @Override
    public Copia buscarCopias(Long isbn) {
        try{
            cdao
        }
    }

    @Override
    public Copia buscarCopia(int idCopia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean adicionarCopia(Long isbn, Copia copia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean removerCopia(Long isbn, Copia copia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean marcarEmprestadoCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean marcarDevolverCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean marcarDisponivelCopia(int idCopia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean removerObra(Obra obra) {
        // TODO Auto-generated method stub
        
    }

    // TODO: Potencialmente nessas funções de adicionar e tudo mais, colocar um retorno para indicar para o usuário
    // Se por exemplo, ele está tentando inserir uma obra que já existe

    // TODO: Substituir métodos pelo uso do dao
    
}
