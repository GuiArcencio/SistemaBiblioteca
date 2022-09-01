package app.Service.impl;

import app.Service.spec.IFuncionarioService;
import app.dao.DevolucaoDAO;
import app.dao.EmprestimoDAO;
import app.dao.ReservaDAO;
import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteEntradaSaidaObras.Reserva;
import app.Domain.PacoteUsuarios.Leitor;

public class FuncionarioService implements IFuncionarioService {
    private EmprestimoDAO edao;
    private DevolucaoDAO ddao;
    private ReservaDAO rdao;

    public FuncionarioService(){
        this.edao = new EmprestimoDAO();
        this.ddao = new DevolucaoDAO();
        this.rdao = new ReservaDAO();
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
}
