package app.Service.impl;

import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.Autor;
import app.Service.spec.IAutorService;
import app.dao.AutorDAO;

public class AutorService implements IAutorService{

    private AutorDAO dao;

    public AutorService() {
        this.dao = new AutorDAO();
    }

    @Override
    public List<Autor> listaAutores() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            // Retorna lista vazia no caso de erro
            System.out.println("[ERRO] A busca de todas as Autors falhou, retornando lista vazia");
            return new ArrayList<Autor>();
        }
    }

    @Override
    public boolean insereAutor(Autor autor) {
        try {
            dao.insert(autor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removerAutor(Long id) {
        try {
            Autor a = dao.getById(id);
            dao.delete(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean alterarAutor(Long id, Autor Autor) {
        try {
            // Verificando se existe 
            if(dao.getById(id) == null){
                System.out.println("Autor de id: " + id + " não foi encontrada");
                return false;
            }
            
            Autor.setId(id);

            dao.update(Autor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
