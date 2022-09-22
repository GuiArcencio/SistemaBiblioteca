package app.Service.impl;

import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Service.spec.IEmprestimoService;
import app.dao.EmprestimoDAO;

public class EmprestimoService implements IEmprestimoService{
    
    private EmprestimoDAO dao;

    public EmprestimoService(){
        this.dao = new EmprestimoDAO();
    }
    
    @Override
    public List<Emprestimo> buscaEmprestimos(Long cpf){
        try{
            return dao.getByLeitor(cpf);
        }catch(Exception e){
            System.out.println("[ERRO] A busca de todos os Emprestimos falhou, retornando lista vazia.");
            return new ArrayList<Emprestimo>();
        }
    }

    @Override
    public Emprestimo buscaEmprestimoPorCopia(Long idCopia){
        try{
            return dao.getByCopia(idCopia);
        }catch(Exception e){
            System.out.println("[ERRO] A busca do Emprestimo falhou, retornando vazio.");
            return null;
        }
    }

    

    @Override
    public boolean realizarEmprestimo(Emprestimo Emprestimo){
        try{
            dao.insert(Emprestimo);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerEmprestimo(Long id){
        try{
            Emprestimo e = dao.getById(id);
            dao.delete(e);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterarEmprestimo(Long id, Emprestimo e){

        try{
            if(dao.getById(id) == null){
                System.out.println("Emprestimo de id:" + id + " não foi encontrado.");
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
    public Emprestimo buscaEmprestimo(Long id){
        try{
            return dao.getById(id);
        }catch(Exception e){
            System.out.println("[ERRO] Emprestimo de id:" + id + " não foi encontrado. Retornando objeto vazio.");
            return null;
        }
    }
}
