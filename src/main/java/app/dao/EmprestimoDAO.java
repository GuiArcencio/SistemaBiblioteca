package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Date;

import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;

public class EmprestimoDAO extends GenericDAO {

    private CopiaDAO cdao;
    private LeitorDAO ldao;
    private FuncionarioDAO fdao;

    public void insert(Emprestimo emprestimo){
        String sql = "INSERT INTO Emprestimo (dataEmprestimo, dataPrevistaDevolucao, funcionarioResponsavel, leitor, codigoCopia, atrasado) VALUES (?, ?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(emprestimo.getDataEmprestimo().getTime());
            statement.setDate(1, mySQLDate);
            java.sql.Date mySQLDate2 = new java.sql.Date(emprestimo.getDataPrevistaDevolucao().getTime());
            statement.setDate(2, mySQLDate2);
            
            //ATENCAO: Substituir pelo metodo getFuncionario().getId() quando este for implementado
            statement.setLong(3, emprestimo.getFuncionarioResponsavel().getId());
            statement.setLong(4, emprestimo.getId());
            statement.setLong(5, emprestimo.getLeitor().getId());
            statement.setLong(6, emprestimo.getCopia().getId());
            statement.setBoolean(7, emprestimo.getAtrasado());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Emprestimo emprestimo) {
        String sql = "UPDATE Emprestimo SET dataEmprestimo = ?, dataPrevistaDevolucao = ?, funcionarioResponsavel = ?, leitor = ?, codigoCopia = ?, atrasado = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(emprestimo.getDataEmprestimo().getTime());
            statement.setDate(1, mySQLDate);
            java.sql.Date mySQLDate2 = new java.sql.Date(emprestimo.getDataPrevistaDevolucao().getTime());
            statement.setDate(2, mySQLDate2);

            //ATENCAO: Substituir pelo metodo getFuncionario().getId() quando este for implementado
            statement.setLong(3, emprestimo.getFuncionarioResponsavel().getId());
            statement.setLong(4, emprestimo.getId());
            statement.setLong(5, emprestimo.getLeitor().getId());
            statement.setLong(6, emprestimo.getCopia().getId());
            statement.setBoolean(7, emprestimo.getAtrasado());
            statement.setLong(8, emprestimo.getId());
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Emprestimo emprestimo){
        String sql = "DELETE FROM Emprestimo where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, emprestimo.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Emprestimo getById(Long id){
        Emprestimo emprestimo = null;

        String sql = "SELECT * from Emprestimo WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Date dataEmprestimo = resultSet.getDate("dataEmprestimo");
                Date dataPrevistaDevolucao = resultSet.getDate("dataPrevistaDevolucao");
                
                //ATENCAO: Adicionar quando funcionario for implementado
                //Funcionario funcionarioResponsavel = new Funcionario(resultSet.getLong("funcionarioResponsavel");
                //ou adicione 
                Funcionario funcionarioResponsavel = fdao.getById(resultSet.getLong("funcionarioResponsavel"));

                
                Leitor leitor = ldao.getById(resultSet.getLong("leitor"));
                Copia copia = cdao.getById(resultSet.getLong("codigoCopia"));
                boolean atrasado = resultSet.getBoolean("atrasado");

                emprestimo = new Emprestimo(id, dataEmprestimo, dataPrevistaDevolucao, funcionarioResponsavel, copia, leitor, atrasado);

            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return emprestimo;

    }

}

