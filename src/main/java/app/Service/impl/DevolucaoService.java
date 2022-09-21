package app.Service.impl;

import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Service.spec.IDevolucaoService;
import app.dao.DevolucaoDAO;

public class DevolucaoService implements IDevolucaoService{
    
    private DevolucaoDAO dao;

    public DevolucaoService(){
        this.dao = new DevolucaoDAO();
    }
    
    @Override
    public List<Devolucao> buscaDevolucoes(Long idUsuario){
        try{
            return dao.getAllByLeitorId(idUsuario);
        }catch(Exception e){
            System.out.println("[ERRO] A busca de todas as Devolucoes falhou, retornando lista vazia.");
            return new ArrayList<Devolucao>();
        }
    }

    @Override
    public boolean realizarDevolucao(Devolucao Devolucao){
        try{
            dao.insert(Devolucao);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerDevolucao(Long id){
        try{
            Devolucao e = dao.getById(id);
            dao.delete(e);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterarDevolucao(Long id, Devolucao e){

        try{
            if(dao.getById(id) == null){
                System.out.println("Devolucao de id:" + id + " não foi encontrada.");
                return false;
            }
            e.setId(id);
            dao.update(e);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Devolucao buscaDevolucao(Long id){
        try{
            return dao.getById(id);
        }catch(Exception e){
            System.out.println("[ERRO] Devolucao de id:" + id + " não foi encontrada. Retornando objeto vazio.");
            return null;
        }
    }
}
