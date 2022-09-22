package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.Domain.PacoteObras.RelObraAutor;
import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Obra;

public class RelObraAutorDAO extends GenericDAO{
    
    private ObraDAO odao;
    private AutorDAO adao;

    public RelObraAutorDAO(){
        this.odao = new ObraDAO();
        this.adao = new AutorDAO();
    }

    public void insert(RelObraAutor roa) throws SQLException{
        String sql = "INSERT INTO relobraautor (codigo_autor, codigo_obra) VALUES (?, ?)";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, roa.getAutor().getId());
        statement.setLong(2, roa.getObra().getCodigo());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    public void update(RelObraAutor roa) throws SQLException{
        String sql = "UPDATE relobraautor SET codigo_autor = ?, codigo_obra = ? WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, roa.getAutor().getId());
        statement.setLong(2, roa.getObra().getCodigo());
        statement.setLong(3, roa.getId());

        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    public void delete(RelObraAutor reo) throws SQLException{
        String sql = "DELETE FROM relobraautor WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, reo.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    public RelObraAutor getById(Long id) throws SQLException{
        RelObraAutor roa = null;
        String sql = "SELECT * FROM relobraautor WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Autor autor = adao.getById(resultSet.getLong("codigo_autor"));
            Obra obra = odao.getByCodigo(resultSet.getLong("codigo_obra"));
            roa = new RelObraAutor(id, autor, obra);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return roa;
    }

    public List<Obra> getAllByAutor(Long codigo_autor) throws SQLException{
        List<Obra> lista = new ArrayList<>();
        String sql = "SELECT * FROM relobraautor WHERE codigo_autor = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, codigo_autor);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            Obra obra = odao.getByCodigo(resultSet.getLong("codigo_obra"));
            lista.add(obra);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return lista;
    }

    public List<Autor> getAllByObra(Long codigo_obra) throws SQLException{
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM relobraautor WHERE codigo_obra = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, codigo_obra);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            Autor autor = adao.getById(resultSet.getLong("codigo_autor"));

            lista.add(autor);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return lista;
    }
}
