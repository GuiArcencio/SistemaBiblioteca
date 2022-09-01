package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteEntradaSaidaObras.Devolucao;

public class DevolucaoDAO extends GenericDAO {

    private EmprestimoDAO edao;

    public void insert(Devolucao devolucao){
        String sql = "INSERT INTO Devolucao (dataDevolucao, multaTotal, codigo_emprestimo) VALUES (?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(devolucao.getDataDevolucao().getTime());
            statement.setDate(1, mySQLDate);
            statement.setDouble(2, devolucao.getMultaTotal());
            
            statement.setLong(3, devolucao.getEmprestimoCorrespondente().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Devolucao devolucao) {
        String sql = "UPDATE Devolucao SET dataDevolucao = ?, multaTotal = ?, codigo_emprestimo = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(devolucao.getDataDevolucao().getTime());
            statement.setDate(1, mySQLDate);
            statement.setDouble(2, devolucao.getMultaTotal());

            statement.setLong(3, devolucao.getEmprestimoCorrespondente().getId());

            statement.setLong(4, devolucao.getId());
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Devolucao devolucao){
        String sql = "DELETE FROM Devolucao where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, devolucao.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Devolucao getById(Long id){
        Devolucao devolucao = null;

        String sql = "SELECT * from Devolucao WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Date dataDevolucao = resultSet.getDate("dataDevolucao");
                double multaTotal = resultSet.getDouble("multaTotal");
                Emprestimo emprestimo = edao.getById(resultSet.getLong("codigo_emprestimo"));

                devolucao = new Devolucao(id, dataDevolucao, multaTotal, emprestimo);

            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return devolucao;

    }


    public List<Devolucao> getAllByLeitorId(Long leitorId){
        List<Devolucao> listaDevolucao = new ArrayList<>();

        String sql = "SELECT * from Devolucao WHERE codigo_emprestimo in (SELECT id FROM Emprestimo WHERE leitor = ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, leitorId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id"); 
                Date dataDevolucao = resultSet.getDate("dataDevolucao");
                double multaTotal = resultSet.getDouble("multaTotal");
                Emprestimo emprestimo = edao.getById(resultSet.getLong("codigo_emprestimo"));

                Devolucao devolucao = new Devolucao(id, dataDevolucao, multaTotal, emprestimo);

                listaDevolucao.add(devolucao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaDevolucao;

    }
}

