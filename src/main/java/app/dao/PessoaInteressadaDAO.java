package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.PessoaInteressada;

public class PessoaInteressadaDAO extends GenericDAO{

    private LeitorDAO ldao;

    public PessoaInteressadaDAO(){
        this.ldao = new LeitorDAO();
    }

    public void insert(PessoaInteressada pi) throws SQLException{
        String sql = "INSERT INTO pessoainteressada (leitorId, obraCodigo) VALUES (?, ?) ";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, pi.getLeitorId());
        statement.setLong(2, pi.getObraCodigo());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }

    public void update(PessoaInteressada pi) throws SQLException{
        String sql = "UPDATE pessoainteressada SET leitorId = ?, obraCodigo = ? WHERE id = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, pi.getLeitorId());
        statement.setLong(2, pi.getObraCodigo());
        statement.setLong(3, pi.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }


    public void delete(PessoaInteressada pi) throws SQLException{
        String sql = "DELETE FROM pessoainteressada where id = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, pi.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
    
    }


    public PessoaInteressada getById(Long id) throws SQLException{
        PessoaInteressada pi = null;

        String sql = "SELECT * from pessoainteressada WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Long leitorId = resultSet.getLong("leitorId");
            Long obraCodigo = resultSet.getLong("obraCodigo");
            pi = new PessoaInteressada(id, leitorId, obraCodigo);
        }
        resultSet.close();
        statement.close();
        conn.close();

        return pi;

    }

    public List<PessoaInteressada> getAll() throws SQLException{
        
        List<PessoaInteressada> listaPessoaInteressadas = new ArrayList<>();

        String sql = "SELECT * from pessoainteressada";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            Long leitorId = resultSet.getLong("leitorId");
            Long obraCodigo = resultSet.getLong("obraCodigo");
            PessoaInteressada pi = new PessoaInteressada(id, leitorId, obraCodigo);
            listaPessoaInteressadas.add(pi);
        }
        resultSet.close();
        statement.close();
        conn.close();

        return listaPessoaInteressadas;
    }

    public List<Leitor> getAllPessoas(Long obraCodigo) throws SQLException{
        
        List<Leitor> listaPessoaInteressadas = new ArrayList<>();

        String sql = "SELECT * from pessoainteressada WHERE obraCodigo = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, obraCodigo);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long leitorId = resultSet.getLong("leitorId");
            Leitor leitor = ldao.getById(leitorId);
            listaPessoaInteressadas.add(leitor);
        }
        resultSet.close();
        statement.close();
        conn.close();

        return listaPessoaInteressadas;
    }
}
