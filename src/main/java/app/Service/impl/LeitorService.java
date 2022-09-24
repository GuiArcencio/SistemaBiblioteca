package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteUsuarios.Leitor;
import app.Service.spec.ILeitorService;
import app.dao.LeitorDAO;

public class LeitorService implements ILeitorService{
    private LeitorDAO ldao;

    public LeitorService(){
        this.ldao = new LeitorDAO();
    }

    @Override
    public List<Leitor> getLeitores() {
        try{
            return ldao.getAll();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRO] Falha na busca de todos os leitores");
            return new ArrayList<Leitor>();
        }
        
    }

    @Override
    public Leitor getLeitor(Long id) {
        try{
            return ldao.getById(id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Leitor buscaPorDocumento(Long documento){
        try{
            return ldao.getByDocumentId(documento);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRO] Falaha na busca do leitor. Verifique o documento informado.");
            return null;
        }
    }

    @Override
    public boolean insereLeitor(Leitor leitor){
        try{
            ldao.insert(leitor);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean removeLeitor(Long id) {
        try {
            Leitor leitor = ldao.getById(id);
            ldao.delete(leitor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean alteraLeitor(Long id, Leitor leitor) {
        try {
            // Verificando se existe 
            if(ldao.getById(id) == null){
                System.out.println("Leitor de id: " + id + " não foi encontrado");
                return false;
            }
            
            leitor.setId(id);

            ldao.update(leitor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
