package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteUsuarios.Endereco;
import app.Service.spec.IEnderecoService;
import app.dao.EnderecoDAO;

public class EnderecoService implements IEnderecoService{
    
    private EnderecoDAO dao;

    public EnderecoService(){
        this.dao = new EnderecoDAO();
    }
    
    @Override
    public List<Endereco> buscaEnderecos(){
        try{
            return dao.getAll();
        }catch(SQLException e){
            System.out.println("[ERRO] A busca de todos os endereços falhou, retornando lista vazia.");
            return new ArrayList<Endereco>();
        }
    }

    @Override
    public boolean insereEndereco(Endereco endereco){
        try{
            dao.insert(endereco);
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean removeEndereco(Long id){
        try{
            Endereco e = dao.getById(id);
            dao.delete(e);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alteraEndereco(Long id, Endereco e){

        try{
            if(dao.getById(id) == null){
                System.out.println("Endereço de id:" + id + " não foi encontrado.");
                return false;
            }
            e.setId(id);
            dao.update(e);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }

    @Override
    public Endereco buscaEnderecoPorId(Long id){
        try{
            return dao.getById(id);
        }catch(SQLException e){
            System.out.println("[ERRO] Endereço de id:" + id + " não foi encontrado. Retornando endereço vazio.");
            return new Endereco();
        }
    }
}
