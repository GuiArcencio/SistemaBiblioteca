package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


import app.Domain.PacoteObras.Editora;


public class EditoraDAO extends GenericDAO{

    public void insert(Editora editora) throws SQLException{
        String sql = "INSERT INTO editora (nome) VALUES (?) ";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, editora.getNome());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }

    public void update(Editora editora) throws SQLException{
        String sql = "UPDATE editora SET nome = ? WHERE id = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, editora.getNome());
        statement.setLong(2, editora.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }


    public void delete(Editora editora) throws SQLException{
        String sql = "DELETE FROM editora where id = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, editora.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
    
    }


    public Editora getById(Long id) throws SQLException{
        Editora editora = null;

        String sql = "SELECT * from editora WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String nome = resultSet.getString("nome");
            editora = new Editora(id, nome);
        }
        resultSet.close();
        statement.close();
        conn.close();

        return editora;

    }

    public List<Editora> getAll() throws SQLException{
        
        List<Editora> listaEditoras = new ArrayList<>();

        String sql = "SELECT * from editora";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String nome = resultSet.getString("nome");
            Editora editora = new Editora(id, nome);
            listaEditoras.add(editora);
        }
        resultSet.close();
        statement.close();
        conn.close();

        return listaEditoras;

    }

}

