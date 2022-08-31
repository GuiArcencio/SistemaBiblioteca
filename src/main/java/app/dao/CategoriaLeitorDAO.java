package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import app.Leitor.Leitor;
import app.CategoriaLeitor.CategoriaLeitor;

public class CategoriaLeitorDAO extends GenericDAO {
    
    public void insert(CategoriaLeitor categoriaLeitor){
        String sql = "INSERT INTO CategoriaLeitor (maximoDiasEmprestimo, descricao) VALUES (?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setInt(1, categoriaLeitor.getMaximoDiasEmprestimo());
            statement.setString(1, categoriaLeitor.getEmail());
            statement.setString(2, categoriaLeitor.getDocumentoId());
            statement.setBoolean(3, categoriaLeitor.getGrupoAcademico());
            statement.setLong(4, categoriaLeitor.getCategoria().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(CategoriaLeitor categoriaLeitor) {
        String sql = "UPDATE CategoriaLeitor SET maximoDiasEmprestimo = ?, descricao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, categoriaLeitor.getMaximoDiasEmprestimo());
            statement.setString(2, categoriaLeitor.getDescricao());
            statement.setLong(3, categoriaLeitor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(CategoriaLeitor categoriaLeitor){
        String sql = "DELETE FROM CategoriaLeitor where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, categoriaLeitor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public CategoriaLeitor getById(Long id){
        CategoriaLeitor categoriaLeitor = null;

        String sql = "SELECT * from CategoriaLeitor WHERE id = ?";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int maximoDiasEmprestimo = resultSet.getInt("maximoDiasEmprestimo");
                String descricao = resultSet.getString("descricao");

                categoriaLeitor = new CategoriaLeitor(id, maximoDiasEmprestimo, descricao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categoriaLeitor;

    }
}
