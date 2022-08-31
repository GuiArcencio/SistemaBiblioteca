package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.CategoriaLeitor;

public class CategoriaLeitorDAO extends GenericDAO {
    
    public void insert(CategoriaLeitor categoriaLeitor) throws SQLException{
        String sql = "INSERT INTO CategoriaLeitor (maximoDiasEmprestimo, descricao) VALUES (?, ?) ";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement = conn.prepareStatement(sql);
        statement.setInt(1, categoriaLeitor.getMaximoDiasEmprestimo());
        statement.setString(2, categoriaLeitor.getDescricao());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    public void update(CategoriaLeitor categoriaLeitor) throws SQLException {
        String sql = "UPDATE CategoriaLeitor SET maximoDiasEmprestimo = ?, descricao = ? WHERE id = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, categoriaLeitor.getMaximoDiasEmprestimo());
        statement.setString(2, categoriaLeitor.getDescricao());
        statement.setLong(3, categoriaLeitor.getId());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    public void delete(CategoriaLeitor categoriaLeitor) throws SQLException{
        String sql = "DELETE FROM CategoriaLeitor where id = ?";

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, categoriaLeitor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
    }


    public CategoriaLeitor getById(Long id) throws SQLException{
        CategoriaLeitor categoriaLeitor = null;

        String sql = "SELECT * from CategoriaLeitor WHERE id = ?";

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

        return categoriaLeitor;

    }

    public List<CategoriaLeitor> getAll() throws SQLException{

    	List<CategoriaLeitor>  listaCategoriasLeitor = new ArrayList<>();

        String sql = "SELECT * from CategoriaLeitor";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int maximoDiasEmprestimo = resultSet.getInt("maximoDiasEmprestimo");
                String descricao = resultSet.getString("p.descricao");

               
                CategoriaLeitor categoriaLeitor = new CategoriaLeitor(id, maximoDiasEmprestimo, descricao);
                listaCategoriasLeitor.add(categoriaLeitor);
            }

        resultSet.close();
        statement.close();
        conn.close();

        return listaCategoriasLeitor;
    }
}
