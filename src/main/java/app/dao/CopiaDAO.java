package app.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Estados.*;


public class CopiaDAO extends GenericDAO{

    public void insert(Copia copia) throws SQLException{
        String sql = "INSERT INTO Copia (estado, obra_id) VALUES (?, ?) ";

        
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, copia.getTipoEstado());
            statement.setLong(2, copia.getObraId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        
    }

    public void update(Copia copia) {
        String sql = "UPDATE Copia SET estado = ?, obra_id = ? WHERE id = ?";

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
                String statestr = resultSet.getString("estado");
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

    public List<Copia> getAllByObraId(Long obraId){
        List<Copia> copias = new ArrayList<>();

        String sql = "SELECT * from Copia WHERE obra_id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, obraId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String statestr = resultSet.getString("estado");
                Long id = resultSet.getLong("id");

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

                Copia copia = new Copia(id, state, obraId);
                copias.add(copia);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return copias;

    }

    public List<Copia> getAllDisponivelByObraId(Long obraId){
        List<Copia> copias = new ArrayList<>();

        String sql = "SELECT * from Copia WHERE obra_id = ? AND estado = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, obraId);
            statement.setString(2, "Disponivel");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
            
                Copia copia = new Copia(id, Disponivel.getInstancia(), obraId);
                copias.add(copia);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return copias;

    }
}

