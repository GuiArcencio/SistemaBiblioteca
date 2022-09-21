package app.Service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.PessoaInteressada;
import app.Service.spec.IPessoaInteressadaService;
import app.dao.PessoaInteressadaDAO;
import app.dao.ObraDAO;

public class PessoaInteressadaService implements IPessoaInteressadaService{

    private PessoaInteressadaDAO dao;
    private ObraDAO odao;

    public PessoaInteressadaService(){
        this.dao = new PessoaInteressadaDAO();
        this.odao = new ObraDAO();
    }

    @Override
    public List<Leitor> buscaPessoasPorObra(Long obraCodigo){
        try{
            return dao.getAllPessoas(obraCodigo);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRO] Não existem leitores interessados nesta obra.");
            return new ArrayList<Leitor>();
        }
    }

    @Override
    public List<PessoaInteressada> buscaTodos(){
        try{
            return dao.getAll();
        }catch(SQLException e){
            System.out.println("[ERRO] A busca de todas as PessoaInteressada falhou, retornando lista vazia.");
            return new ArrayList<PessoaInteressada>();
        }
    }

    @Override
    public boolean insere(PessoaInteressada pi){
        try{
            Obra obra = odao.getByCodigo(pi.getObraCodigo());
            dao.insert(pi);
            obra.registrar(pi);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRP] Falha na inserção.");
            return false;
        }
    }

    @Override
    public boolean remove(Long id){
        try{
            PessoaInteressada pi = dao.getById(id);
            dao.delete(pi);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRP] Falha na remoção.");
            return false;
        }
    }

    @Override
    public boolean atualiza(Long id, PessoaInteressada pi){
        try{
            if(dao.getById(id) == null){
                System.out.println("PessoaInteressada de id:" + id + " não foi encontrada.");
                return false;
            }
            pi.setId(id);
            dao.update(pi);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("[ERRP] Falha na atualização.");
            return false;
        }
    }
    
}
