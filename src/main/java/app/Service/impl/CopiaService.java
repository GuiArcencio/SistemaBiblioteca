package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.Copia;
import app.Service.spec.ICopiaService;
import app.dao.CopiaDAO;

public class CopiaService implements ICopiaService {

    private CopiaDAO dao;

    public CopiaService() {
        this.dao = new CopiaDAO();
    }

    @Override
    public List<Copia> buscaCopias(Long codigoObra) {
        try {
            return dao.getAllByObraId(codigoObra);
        } catch (Exception e) {
            System.out.println("[ERRO] A busca das Copias falhou, retornando lista vazia.");
            return new ArrayList<Copia>();
        }
    }

    @Override
    public Copia buscaCopia(Long id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            System.out.println("[ERRO] A busca da Copia falhou, retornando objeto vazio.");
            return null;
        }
    }

    @Override
    public boolean insereCopia(Copia Copia) {
        try {
            dao.insert(Copia);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerCopia(Long id) {
        try {
            Copia co = new Copia(id);
            dao.delete(co);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterarCopia(Long id, Copia co) {

        try {
            if (dao.getById(id) == null) {
                System.out.println("Copia de id:" + id + " não foi encontrada.");
                return false;
            }
            co.setId(id);
            dao.update(co);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean mudarEstado(Long id, Copia copia) {
        try {
            copia.setId(id);
            dao.update(copia);
        } catch (Exception e) {
            System.out.println(
                    "[ERRO] Copia de id:" + id + " não foi encontrada. Retornando false.");
            return false;
        }
        return true;
    }

    @Override
    public List<Copia> buscarDisponiveisByObraId(Long obraId) {
        try {
            return dao.getAllDisponivelByObraId(obraId);
        } catch (Exception e) {
            System.out.println("[ERRO] obraId não existe ou não há obras disponiveis, retornando lista vazia.");
            return new ArrayList<Copia>();
        }
    }
}
