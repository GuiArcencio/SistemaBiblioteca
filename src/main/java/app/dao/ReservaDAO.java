package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Date;

import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteEntradaSaidaObras.Reserva;

public class ReservaDAO extends GenericDAO {

    private CopiaDAO cdao;
    private LeitorDAO ldao;
    private FuncionarioDAO fdao;

    public void insert(Reserva reserva){
        String sql = "INSERT INTO Reserva (dataReserva, dataPrevistaRetirada, dataPrevistaDevolucao, funcionarioResponsavel, leitor, copiaReservada) VALUES (?, ?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(reserva.getDataReserva().getTime());
            statement.setDate(1, mySQLDate);
            java.sql.Date mySQLDate2 = new java.sql.Date(reserva.getDataPrevistaRetirada().getTime());
            statement.setDate(2, mySQLDate2);
            java.sql.Date mySQLDate3 = new java.sql.Date(reserva.getDataPrevistaDevolucao().getTime());
            statement.setDate(3, mySQLDate3);
            
            //ATENCAO: Substituir pelo metodo getFuncionario().getId() quando este for implementado
            statement.setLong(4, reserva.getFuncionarioResponsavel().getId());
            statement.setLong(5, reserva.getId());

            statement.setLong(6, reserva.getLeitor().getId());
            statement.setLong(7, reserva.getCopia().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Reserva reserva) {
        String sql = "UPDATE Reserva SET dataReserva = ?, dataPrevistaRetirada = ?, dataPrevistaDevolucao = ?, funcionarioResponsavel = ?, leitor = ?, copiaReservada = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            java.sql.Date mySQLDate = new java.sql.Date(reserva.getDataReserva().getTime());
            statement.setDate(1, mySQLDate);
            java.sql.Date mySQLDate2 = new java.sql.Date(reserva.getDataPrevistaRetirada().getTime());
            statement.setDate(2, mySQLDate2);
            java.sql.Date mySQLDate3 = new java.sql.Date(reserva.getDataPrevistaDevolucao().getTime());
            statement.setDate(3, mySQLDate3);

            //ATENCAO: Substituir pelo metodo getFuncionario().getId() quando este for implementado
            statement.setLong(4, reserva.getFuncionarioResponsavel().getId());
            statement.setLong(5, reserva.getId());

            statement.setLong(6, reserva.getLeitor().getId());
            statement.setLong(7, reserva.getCopia().getId());
            statement.setLong(8, reserva.getId());
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Reserva reserva){
        String sql = "DELETE FROM Reserva where id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, reserva.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Reserva getById(Long id){
        Reserva reserva = null;

        String sql = "SELECT * from Reserva WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){

                Date dataReserva = resultSet.getDate("dataReserva");
                Date dataPrevistaRetirada = resultSet.getDate("dataPrevistaRetirada");
                Date dataPrevistaDevolucao = resultSet.getDate("dataPrevistaDevolucao");

                
                Funcionario funcionarioResponsavel = fdao.getById(resultSet.getLong("funcionarioResponsavel"));

                
                Leitor leitor = ldao.getById(resultSet.getLong("leitor"));
                Copia copia = cdao.getById(resultSet.getLong("codigoCopia"));

                reserva = new Reserva(id, dataReserva, dataPrevistaRetirada, dataPrevistaDevolucao, funcionarioResponsavel, leitor, copia);

            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return reserva;

    }

        public Reserva getByLeitorCopia(Long leitorId, Long copiaId){
        Reserva reserva = null; 

        String sql = "SELECT * from Reserva WHERE leitor = ?, copiaReservada = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, leitorId);
            statement.setLong(2, copiaId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){ 

                Date dataReserva = resultSet.getDate("dataReserva");
                Date dataPrevistaRetirada = resultSet.getDate("dataPrevistaRetirada");
                Date dataPrevistaDevolucao = resultSet.getDate("dataPrevistaDevolucao");


                Funcionario funcionarioResponsavel = fdao.getById(resultSet.getLong("funcionarioResponsavel"));


                Leitor leitor = ldao.getById(resultSet.getLong("leitor"));
                Copia copia = cdao.getById(resultSet.getLong("codigoCopia"));

                reserva = new Reserva(id, dataReserva, dataPrevistaRetirada, dataPrevistaDevolucao, funcionarioResponsavel, leitor, copia);

            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }   
        return reserva;

    }


}

