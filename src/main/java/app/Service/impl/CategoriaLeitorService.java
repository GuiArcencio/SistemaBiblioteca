package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteUsuarios.CategoriaLeitor;
import app.Service.spec.ICategoriaLeitorService;
import app.dao.CategoriaLeitorDAO;

public class CategoriaLeitorService implements ICategoriaLeitorService{

    private CategoriaLeitorDAO dao;

    public CategoriaLeitorService() {
        this.dao = new CategoriaLeitorDAO();
    }

    @Override
    public List<CategoriaLeitor> buscaCategorias() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            // Retorna lista vazia no caso de erro
            System.out.println("[ERRO] A busca de todas as categorias falhou, retornando lista vazia");
            return new ArrayList<CategoriaLeitor>();
        }
    }

    @Override
    public boolean insereCategoria(CategoriaLeitor categoria) {
        try {
            dao.insert(categoria);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean removerCategoria(Long id) {
        try {
            CategoriaLeitor c = dao.getById(id);
            dao.delete(c);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean alterarCategoria(Long id, CategoriaLeitor categoria) {
        try {
            // Verificando se existe 
            if(dao.getById(id) == null){
                System.out.println("Categoria de id: " + id + " não foi encontrada");
                return false;
            }
            
            categoria.setId(id);

            dao.update(categoria);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
}
