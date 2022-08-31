package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import app.CategoriaObra.CategoriaObra;

public class CategoriaObraDAO extends GenericDAO{

    public void insert(CategoriaObra categoriaObra){
        String sql = "INSERT INTO CategoriaObra (descricao, maximoDiasEmprestimo, taxaMulta) VALUES (?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(2, categoriaObra.getDescricao());
            statement.setInt(3, categoriaObra.getMaximoDiasEmprestimo());
            statement.setDouble(4, categoriaObra.getTaxaMulta());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void update(CategoriaObra categoriaObra) {
        String sql = "UPDATE CategoriaObra SET descricao = ?, maximoDiasEmprestimo = ?, taxaMulta = ? WHERE codigo = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, categoriaObra.getDescricao());
            statement.setInt(2, categoriaObra.getMaximoDiasEmprestimo());
            statement.setDouble(3, categoriaObra.getTaxaMulta());
            statement.setInt(4, categoriaObra.getCodigo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(CategoriaObra categoriaObra){
        String sql = "DELETE FROM CategoriaObra WHERE codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, categoriaObra.getCodigo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public CategoriaObra getByCodigo(int codigo){
        CategoriaObra categoriaObra = null;

        String sql = "SELECT * from CategoriaObra WHERE codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String descricao = resultSet.getString("descricao");
                int maximoDiasEmprestimo = resultSet.getInt("maximoDiasEmprestimo");
                double taxaMulta = resultSet.getDouble("taxaMulta");

                categoriaObra = new CategoriaObra(codigo, descricao, maximoDiasEmprestimo, taxaMulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categoriaObra;

    }

}

