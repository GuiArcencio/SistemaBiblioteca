package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.CategoriaObra;
import app.Service.spec.ICategoriaObraService;
import app.dao.CategoriaObraDAO;

public class CategoriaObraService implements ICategoriaObraService{
    
    private CategoriaObraDAO dao;

    public CategoriaObraService(){
        this.dao = new CategoriaObraDAO();
    }
    
    @Override
    public List<CategoriaObra> buscaCategoriasObra(){
        try{
            return dao.getAll();
        }catch(SQLException e){
            System.out.println("[ERRO] A busca de todas as CategoriasObra falhou, retornando lista vazia.");
            return new ArrayList<CategoriaObra>();
        }
    }

    @Override
    public boolean insereCategoriaObra(CategoriaObra categoriaObra){
        try{
            dao.insert(categoriaObra);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeCategoriaObra(Long codigo){
        try{
            CategoriaObra co = dao.getByCodigo(codigo);
            dao.delete(co);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alteraCategoriaObra(Long codigo, CategoriaObra co){

        try{
            if(dao.getByCodigo(codigo) == null){
                System.out.println("CategoriaObra de codigo:" + codigo + " não foi encontrada.");
                return false;
            }
            co.setCodigo(codigo);
            dao.update(co);
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public CategoriaObra buscaCategoriaObraPorCodigo(Long codigo){
        try{
            return dao.getByCodigo(codigo);
        }catch(SQLException e){
            System.out.println("[ERRO] CategoriaObra de codigo:" + codigo + " não foi encontrada. Retornando objeto vazio.");
            return new CategoriaObra();
        }
    }
}
