package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.Domain.PacoteObras.Autor;


public class AutorDAO extends GenericDAO{

    public void insert(Autor autor){
        String sql = "INSERT INTO Autor (nome, iniciais) VALUES (?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getIniciais());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Autor autor) {
        String sql = "UPDATE Autor SET nome = ?, iniciais = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getIniciais());
            statement.setLong(3, autor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Autor autor){
        String sql = "DELETE FROM Autor where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, autor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Autor getById(Long id){
        Autor autor = null;

        String sql = "SELECT * from Autor WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String nome = resultSet.getString("nome");
                String iniciais = resultSet.getString("iniciais");

                autor = new Autor(id, nome, iniciais);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return autor;

    }

}

