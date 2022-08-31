package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.Copia.Copia;
import app.Estados.*;


public class CopiaDAO extends GenericDAO{

    public void insert(Copia copia){
        String sql = "INSERT INTO Copia (state, obra_id) VALUES (?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, copia.getState().getState());
            statement.setLong(2, copia.getObraId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Copia copia) {
        String sql = "UPDATE Copia SET state = ?, obra_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, copia.getState().getState());
            statement.setLong(2, copia.getObraId());
            statement.setLong(3, copia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Copia copia){
        String sql = "DELETE FROM Copia where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, copia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Copia getById(Long id){
        Copia copia = null;

        String sql = "SELECT * from Copia WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String statestr = resultSet.getString("state");
                Long obraId = resultSet.getLong("obra_id");
            
                State state = null;

                if (statestr == "Emprestado"){
                    state = Emprestado.getInstancia();
                }

                else if(statestr == "Reservado") {
                    state = Reservado.getInstancia();
                }
            
                else {
                    state = Disponivel.getInstancia();
                }

                copia = new Copia(id, state, obraId);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return copia;

    }

}

