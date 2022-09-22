package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


import app.Domain.PacoteUsuarios.Endereco;

public class EnderecoDAO extends GenericDAO{

    public void insert(Endereco endereco) throws SQLException{
        String sql = "INSERT INTO endereco (logradouro, numero, cep, cidade, estado) VALUES (?, ?, ?, ?, ?) ";

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
        
    }

    public void update(Endereco endereco) throws SQLException{
        String sql = "UPDATE endereco SET logradouro = ?, numero = ?, cep = ?, cidade = ?, estado = ? WHERE id = ?";
        
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
        
    }

    public void delete(Endereco endereco) throws SQLException{
        String sql = "DELETE FROM endereco where id = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, endereco.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }


    public Endereco getById(Long id) throws SQLException{
        Endereco endereco = null;

        String sql = "SELECT * from endereco WHERE id = ?";

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

        return endereco;

    }

    public List<Endereco> getAll() throws SQLException{
        List<Endereco> listaEnderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String logradouro = resultSet.getString("logradouro");
            int numero = resultSet.getInt("numero");
            int cep = resultSet.getInt("cep");
            String cidade = resultSet.getString("cidade");
            String estado = resultSet.getString("estado");

            Endereco endereco = new Endereco(id, logradouro, numero, cep, cidade, estado);
            listaEnderecos.add(endereco);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return listaEnderecos;
    }

}
