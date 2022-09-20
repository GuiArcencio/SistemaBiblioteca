package app.Service.impl;

import app.Service.spec.IFuncionarioService;
import app.dao.DevolucaoDAO;
import app.dao.EmprestimoDAO;
import app.dao.FuncionarioDAO;
import app.dao.ReservaDAO;

import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteEntradaSaidaObras.Reserva;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;

public class FuncionarioService implements IFuncionarioService {
    private EmprestimoDAO edao;
    private DevolucaoDAO ddao;
    private ReservaDAO rdao;
    private FuncionarioDAO fdao;

    public FuncionarioService(){
        this.edao = new EmprestimoDAO();
        this.ddao = new DevolucaoDAO();
        this.rdao = new ReservaDAO();
        this.fdao = new FuncionarioDAO();
    }

    @Override
    public boolean alterarEmprestimo(Leitor leitor, Long copiaId){
        try{
            Emprestimo emprestimo = edao.getByLeitorAndCopia(leitor.getId(), copiaId);
            edao.update(emprestimo);
            return true;
        }catch(Exception e){
            System.out.println("[ERRO] Leitor ou Copia não encontrados.");
            return false;
        }
    }

    @Override
    public boolean alterarDevolucao(Leitor leitor, Long copiaId){
        try{
            Emprestimo emprestimo = edao.getByLeitorAndCopia(leitor.getId(), copiaId);
            Devolucao devolucao = ddao.getByEmprestimoId(emprestimo.getId());
            ddao.update(devolucao);
            return true;
        }catch(Exception e){
            System.out.println("[ERRO] Leitor ou Copia não encontrados.");
            return false;
        }
    }

    @Override
    public boolean alterarReserva(Leitor leitor, Long copiaId){
        try{
            Reserva reserva = rdao.getByLeitorAndCopia(leitor.getId(), copiaId);
            rdao.update(reserva);
            return true;
        }catch(Exception e){
            System.out.println("[ERRO] Leitor ou Copia não encontrados.");
            return false;
        }
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Funcionario getFuncionario(Long id) {
        try{
            Funcionario f = fdao.getById(id);
            return f;
        } catch (Exception e){
            System.out.println("[FuncionarioService] Funcionário de ID " + id + " não encontrado");
            return null;
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
}
