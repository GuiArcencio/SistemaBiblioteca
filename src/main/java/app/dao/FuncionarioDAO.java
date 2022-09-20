package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.CategoriaLeitor;
import app.Domain.PacoteUsuarios.Endereco;
//import app.Domain.PacoteUsuarios.Usuario;

public class FuncionarioDAO extends GenericDAO {
    
    public void insert(Funcionario funcionario){
        String sql = "INSERT INTO Usuario (nome, telefone, dataNascimento, endereco, role) VALUES (?, ?, ?, ?, funcionario) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getTelefone());
            java.sql.Date mySQLDate = new java.sql.Date(funcionario.getDataNascimento().getTime());
            statement.setDate(3, mySQLDate);
            statement.setLong(4, funcionario.getEndereco().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Funcionario funcionario) {
        String sql = "UPDATE Usuario SET nome = ?, telefone = ?, dataNascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getTelefone());
            java.sql.Date mySQLDate = new java.sql.Date(funcionario.getDataNascimento().getTime());
            statement.setDate(3, mySQLDate);
            statement.setLong(4, funcionario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Funcionario funcionario){
        String sql = "DELETE FROM Usuario where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, funcionario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> getAll() throws SQLException{
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        String sql = "SELECT * from Usuario WHERE role = FUNC";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

            }
            if(resultSet.next()){
                Long id = Long.parseLong(resultSet.getString("id"));
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("dataNascimento");

                Long enderecoId = resultSet.getLong("endereco_id");
                Endereco endereco = new EnderecoDAO().getById(enderecoId);

                Funcionario funcionario = new Funcionario(id, nome, telefone, dataNascimento, endereco);
                listaFuncionarios.add(funcionario);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaFuncionarios;

    }

    public Funcionario getById(Long id){
        Funcionario funcionario = null;

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

                Long enderecoId = resultSet.getLong("id");
                Endereco endereco = new EnderecoDAO().getById(enderecoId);

                funcionario = new Funcionario(id, nome, telefone, dataNascimento, endereco);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return funcionario;

    }
}
