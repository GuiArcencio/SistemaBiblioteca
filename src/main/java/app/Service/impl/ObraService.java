package app.Service.impl;

import java.util.List;
import java.util.ArrayList;


//import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Service.spec.IObraService;
import app.dao.ObraDAO;
import app.dao.AutorDAO;
import app.dao.CopiaDAO;


public class ObraService implements IObraService{
    private ObraDAO odao;
    //private AutorDAO adao;
    private CopiaDAO cdao;

    public ObraService() {
        this.odao = new ObraDAO();
        //this.adao = new AutorDAO();
        this.cdao = new CopiaDAO();
    }

    @Override
    public List<Obra> buscaObraPorIsbn(Long isbn) {
       try{
            return odao.getAllByIsbn(isbn);
       } catch(Exception e){
            System.out.println("Obra(s) não encontrada! Retornando null.");
            return null;
       }
    }

    @Override
    public Obra buscaObraPorTitulo(String titulo) {
       try{
            return odao.getByTitulo(titulo);
       } catch(Exception e){
            System.out.println("Obra não encontrada! Retornando NULL.");
            return null;
       }
    }

    @Override
    public Obra buscaObraPorCodigo(Long codigo) {
       try{
            return odao.getByCodigo(codigo);
       } catch(Exception e){
            System.out.println("Obra não encontrada! Retornando NULL.");
            return null;
       }
    }

    @Override
    public boolean adicionaObra(Obra obra) {
        try {
            odao.insert(obra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeObra(Obra obra) {
        try {
            odao.delete(obra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
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
            Obra obra = buscaObraPorCodigo(codigo);
            if(obra==null){
                System.out.println("[ERRO] Obra não contrada! Verifique o código informado. Se for uma nova Obra, adicione-a primeiro.");
                return false;
            }
            if(buscarAutor(autor.getId())==null){
                adao.insert(autor, obra);
            }
            List<Autor> listaAutores = new ArrayList<>();
            listaAutores = obra.getAutores();
            listaAutores.add(autor);
            obra.setAutores(listaAutores);
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
            List<Autor> listaAutores = new ArrayList<>();
            listaAutores = obra.getAutores();
            listaAutores.remove(autor);
            obra.setAutores(listaAutores);
            return true;
        } catch (Exception e) {
            return false;
        }  
        
    }
    */

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
       try{
            Obra obra = buscaObraPorCodigo(codigo);
            if(obra == null){
                System.out.println("[ERRO] Obra não encontrada! Verifique o código informado.");
                return false;
            }
            List<String> palavrasChave = new ArrayList<>();
            palavrasChave = obra.getPalavrasChave();
            palavrasChave.add(palavra);
            obra.setPalavrasChave(palavrasChave);
            return true;
       }catch(Exception e){
            return false;
       }
        
    }

    @Override
    public boolean removerPalavraChave(Long codigo, String palavra) {
        try{
            Obra obra = buscaObraPorCodigo(codigo);
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
        }catch(Exception e){
            return false;
        }
        
    }

    @Override
    public List<Copia> buscarCopias(Long codigo) {
        try{
            return cdao.getAllByObraId(codigo);
        }catch(Exception e){
            System.out.println("[ERRO] Não foram encontradas nenhuma cópia desta obra! Verifique o código informado.");
            return null;
        }
    }

    @Override
    public Copia buscarCopia(Long idCopia) {
        try{
            return cdao.getById(idCopia);
        }catch(Exception e){
            System.out.println("[ERRO] Cópia não encontrada! Verifique o id informado.");
            return null;
        }
       
    }

    @Override
    public boolean adicionarCopia(Long codigo, Copia copia) {
        try{
            Obra obra = buscaObraPorCodigo(codigo);
            if(obra == null){
                System.out.println("[ERRO] Obra não encontrada! Verifique o codigo informado.");
                return false;
            }
            if(buscarCopia(copia.getId())==null){
                copia.setObraId(codigo);
                cdao.insert(copia);
            }
            obra.notifyAllObservers();
            return true; 
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean removerCopia(Copia copia) {
        try{
            if(buscarCopia(copia.getId())==null){
                System.out.println("[ERRO] Copia não encontrada! Verifique o id informado.");
                return false;
            }
            cdao.delete(copia);
            return true; 
        }catch(Exception e){
            return false;
        }
    }

    // TODO: Potencialmente nessas funções de adicionar e tudo mais, colocar um retorno para indicar para o usuário
    // Se por exemplo, ele está tentando inserir uma obra que já existe

    // TODO: Substituir métodos pelo uso do dao
    
}
