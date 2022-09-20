package app.Service.impl;

import java.util.List;
import java.sql.SQLException;

import app.Service.spec.IRelObraAutorService;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.RelObraAutor;
import app.Domain.PacoteObras.Autor;
import app.dao.RelObraAutorDAO;

public class RelObraAutorService implements IRelObraAutorService{
    private RelObraAutorDAO dao;

    public RelObraAutorService(){
        this.dao = new RelObraAutorDAO();
    }

    @Override
    public List<Obra> buscaObrasPorAutor(Long codigo_autor){
        try{
            return dao.getAllByAutor(codigo_autor);
        }catch(SQLException e){
            System.out.println("[ERRO] não foram encontradas obras desse autor.");
            return null;
        }
    }

    @Override
    public List<Autor> buscaAutoresPorObra(Long codigo_obra){
        try{
            return dao.getAllByObra(codigo_obra);
        }catch(SQLException e){
            System.out.println("[ERRO] não foram encontrados autores dessa obra.");
            return null;
        }
    }

    @Override
    public RelObraAutor buscaROAPorId(Long id){
        try{
            return dao.getById(id);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean adicionaROA(RelObraAutor roa) {
        try {
            dao.insert(roa);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeROA(Long id) {
        try {
            RelObraAutor roa = dao.getById(id);
            dao.delete(roa);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizaROA(Long id, RelObraAutor roa) {
        try {
            if(dao.getById(id) == null){
                System.out.println("[ERRO] RelObraAutor de id:" + id + " não encontrado");
                return false;
            }
            roa.setId(id);
            dao.update(roa);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
