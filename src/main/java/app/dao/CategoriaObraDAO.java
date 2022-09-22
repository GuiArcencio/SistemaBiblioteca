package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


import app.Domain.PacoteObras.CategoriaObra;

public class CategoriaObraDAO extends GenericDAO{

    public void insert(CategoriaObra categoriaObra) throws SQLException{ 
        String sql = "INSERT INTO categoriaobra (descricao, maximoDiasEmprestimo, taxaMulta) VALUES (?, ?, ?) ";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement = conn.prepareStatement(sql);
        statement.setString(1, categoriaObra.getDescricao());
        statement.setInt(2, categoriaObra.getMaximoDiasEmprestimo());
        statement.setDouble(3, categoriaObra.getTaxaMulta());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }


    public void update(CategoriaObra categoriaObra) throws SQLException{
        String sql = "UPDATE categoriaobra SET descricao = ?, maximoDiasEmprestimo = ?, taxaMulta = ? WHERE codigo = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, categoriaObra.getDescricao());
        statement.setInt(2, categoriaObra.getMaximoDiasEmprestimo());
        statement.setDouble(3, categoriaObra.getTaxaMulta());
        statement.setLong(4, categoriaObra.getCodigo());
        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }

    public void delete(CategoriaObra categoriaObra) throws SQLException{
        String sql = "DELETE FROM categoriaobra WHERE codigo = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, categoriaObra.getCodigo());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    public CategoriaObra getByCodigo(Long codigo) throws SQLException{
        CategoriaObra categoriaObra = null;

        String sql = "SELECT * from categoriaobra WHERE codigo = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, codigo);
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
        
        return categoriaObra;
    }

    public List<CategoriaObra> getAll() throws SQLException{

    	List<CategoriaObra>  listaCategoriasObra = new ArrayList<>();

        String sql = "SELECT * from categoriaobra";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long codigo = resultSet.getLong("codigo");
                String descricao = resultSet.getString("descricao");
                int maximoDiasEmprestimo = resultSet.getInt("maximoDiasEmprestimo");
                double taxaMulta = resultSet.getDouble("taxaMulta");

                CategoriaObra categoriaObra = new CategoriaObra(codigo, descricao, maximoDiasEmprestimo, taxaMulta);
                listaCategoriasObra.add(categoriaObra);
            }

        resultSet.close();
        statement.close();
        conn.close();

        return listaCategoriasObra;
    }

}

