package app.Service.impl;

import app.Service.spec.IUsuarioService;

//import java.sql.SQLException;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteUsuarios.Funcionario;
//import app.Domain.PacoteUsuarios.Usuario;
import app.dao.LeitorDAO;
import app.dao.UsuarioDAO;
import app.dao.FuncionarioDAO;

public class UsuarioService implements IUsuarioService{
    
    private FuncionarioDAO fdao;
    private LeitorDAO ldao;
    private UsuarioDAO udao;

    public UsuarioService(){
        this.fdao = new FuncionarioDAO();
        this.ldao = new LeitorDAO();
        this.udao = new UsuarioDAO();
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
    public boolean insereFuncionario(Funcionario funcionario){
        try{
            fdao.insert(funcionario);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean removeFuncionario(Long id) {
        try {
            Funcionario funcionario = fdao.getById(id);
            fdao.delete(funcionario);
            return true;
        } catch (Exception e) {
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
    @Override
    public boolean alteraFuncionario(Long id, Funcionario funcionario) {
        try {
            // Verificando se existe 
            if(fdao.getById(id) == null){
                System.out.println("Funcionario de id: " + id + " não foi encontrado");
                return false;
            }
            
            funcionario.setId(id);

            fdao.update(funcionario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        try{
            List<Usuario> u = udao.getAll();
            if(u.isEmpty()){
                System.out.println("Nenhum usuário encontrado");
            }
            return u;
        } catch (Exception e){
            System.out.println("Serviço: Erro retornado do SQL");
            System.out.println(e.getMessage());
            return new ArrayList<Usuario>();
        }
    }

    @Override
    public Usuario getUsuario(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
