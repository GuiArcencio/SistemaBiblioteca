package app.Service.impl;

import java.math.BigInteger;
import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Copia;
import app.Service.spec.IEmprestimoService;
import app.dao.EmprestimoDAO;
import app.dao.UsuarioDAO;

public class EmprestimoService implements IEmprestimoService {

    private EmprestimoDAO edao;
    private UsuarioDAO udao;

    public EmprestimoService() {
        this.edao = new EmprestimoDAO();
    }

    @Override
    public List<Emprestimo> buscaEmprestimos(BigInteger idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Devolucao> buscaDevolucoes(BigInteger idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia emprestarObra(BigInteger idUsuario, BigInteger isbn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia devolverObra(BigInteger idUsuario, BigInteger isbn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia reservarObra(BigInteger idUsuario, BigInteger isbn) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
