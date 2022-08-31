package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.Domain.PacoteUsuarios.Endereco;

public class EnderecoDAO extends GenericDAO{

    public void insert(Endereco endereco){
        String sql = "INSERT INTO Endereco (logradouro, numero, cep, cidade, estado) VALUES (?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, endereco.getLogradouro());
            statement.setInt(2, endereco.getNumero());
            statement.setInt(3, endereco.getCep());
            statement.setString(4, endereco.getCidade());
            statement.setString(5, endereco.getEstado());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Endereco endereco) {
        String sql = "UPDATE Endereco SET logradouro = ?, numero = ?, cep = ?, cidade = ?, estado = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, endereco.getLogradouro());
            statement.setInt(2, endereco.getNumero());
            statement.setInt(3, endereco.getCep());
            statement.setString(4, endereco.getCidade());
            statement.setString(5, endereco.getEstado());
            statement.setLong(6, endereco.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Endereco endereco){
        String sql = "DELETE FROM Endereco where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, endereco.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Endereco getById(Long id){
        Endereco endereco = null;

        String sql = "SELECT * from Endereco WHERE id = ?";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String logradouro = resultSet.getString("logradouro");
                int numero = resultSet.getInt("numero");
                int cep = resultSet.getInt("cep");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");

                endereco = new Endereco(id, logradouro, numero, cep, cidade, estado);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return endereco;

    }

}
