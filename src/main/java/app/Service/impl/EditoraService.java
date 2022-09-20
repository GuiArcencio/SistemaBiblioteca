package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.Editora;
import app.Service.spec.IEditoraService;
import app.dao.EditoraDAO;

public class EditoraService implements IEditoraService{
    
    private EditoraDAO dao;

    public EditoraService(){
        this.dao = new EditoraDAO();
    }
    
    @Override
    public List<Editora> buscaEditoras(){
        try{
            return dao.getAll();
        }catch(SQLException e){
            System.out.println("[ERRO] A busca de todas as editoras falhou, retornando lista vazia.");
            return new ArrayList<Editora>();
        }
    }

    @Override
    public boolean insereEditora(Editora editora){
        try{
            dao.insert(editora);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeEditora(Long id){
        try{
            Editora e = dao.getById(id);
            dao.delete(e);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alteraEditora(Long id, Editora e){

        try{
            if(dao.getById(id) == null){
                System.out.println("Editora de id:" + id + " não foi encontrada.");
                return false;
            }
            e.setId(id);
            dao.update(e);
            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Editora buscaEditoraPorId(Long id){
        try{
            return dao.getById(id);
        }catch(SQLException e){
            System.out.println("[ERRO] Editora de id:" + id + " não foi encontrada. Retornando objeto vazio.");
            return new Editora();
        }
    }
}
