package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.Editora.Editora;


public class EditoraDAO extends GenericDAO{

    public void insert(Editora editora){
        String sql = "INSERT INTO Editora (nome) VALUES (?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, editora.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Editora editora) {
        String sql = "UPDATE Editora SET nome = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, editora.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Editora editora){
        String sql = "DELETE FROM Editora where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, editora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Editora getById(Long id){
        Editora editora = null;

        String sql = "SELECT * from Editora WHERE id = ?";

        try{
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
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return editora;

    }

}

