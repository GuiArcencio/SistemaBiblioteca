package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Date;

import app.Usuario.Usuario;
import app.Endereco.Endereco;

public class UsuarioDAO extends GenericDAO {
    
    public void insert(Usuario usuario){
        String sql = "INSERT INTO Usuario (nome, telefone, dataNascimento, endereco, role) VALUES (?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getTelefone());
            java.sql.Date mySQLDate = new java.sql.Date(usuario.getDataNascimento().getTime());
            statement.setDate(3, mySQLDate);
            statement.setLong(4, usuario.getEndereco().getId());
            statement.setString(5, usuario.getRole());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, telefone = ?, dataNascimento = ?, role = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getTelefone());
            java.sql.Date mySQLDate = new java.sql.Date(usuario.getDataNascimento().getTime());
            statement.setDate(3, mySQLDate);
            statement.setString(4, usuario.getRole());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Usuario usuario){
        String sql = "DELETE FROM Usuario where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Usuario getById(Long id){
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE id = ?";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("dataNascimento");
                String role = resultSet.getString("role");

                Long enderecoId = resultSet.getLong("id");
                Endereco endereco = new EnderecoDAO().getById(enderecoId);


                usuario = new Usuario(id, nome, telefone, dataNascimento, endereco, role);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuario;

    }
}
