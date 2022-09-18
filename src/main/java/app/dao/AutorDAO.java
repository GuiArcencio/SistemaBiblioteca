package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Obra;


public class AutorDAO extends GenericDAO{

    public void insert(Autor autor){
        String sql = "INSERT INTO Autor (nome, iniciais) VALUES (?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getIniciais());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertAutorInObra(Autor autor, Obra obra){
        String sql = "INSERT INTO RelObraAutor (codigo_autor, codigo_obra) VALUES (SELECT codigo FROM Autor WHERE nome = ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setLong(2, obra.getCodigo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void update(Autor autor) {
        String sql = "UPDATE Autor SET nome = ?, iniciais = ? WHERE codigo = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getIniciais());
            statement.setLong(3, autor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Autor autor){
        String sql = "DELETE FROM Autor where codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, autor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Autor getById(Long id){
        Autor autor = null;

        String sql = "SELECT * from Autor WHERE codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String nome = resultSet.getString("nome");
                String iniciais = resultSet.getString("iniciais");

                autor = new Autor(id, nome, iniciais);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return autor;

    }

    public List<Autor> getAllByObraId(Long id) {
        List<Autor> lista = new ArrayList<>();

        String sql = "SELECT * from RelObraAutor WHERE codigo_obra = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long autor_id = resultSet.getLong("codigo_autor");
                sql = "SELECT * from Autor WHERE codigo = ?";
                PreparedStatement statement2 = conn.prepareStatement(sql);
                statement2.setLong(1, autor_id);
                ResultSet resultSet2 = statement2.executeQuery();
                if(resultSet2.next()){
                    String nome = resultSet2.getString("nome");
                    String iniciais = resultSet2.getString("iniciais");

                    Autor autor = new Autor(autor_id, nome, iniciais);
                    lista.add(autor);
                
                }
                resultSet2.close();
                statement2.close();
                
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Autor> getAll(){

    	List<Autor>  listaAutores = new ArrayList<>();

        String sql = "SELECT * from Autor";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Long codigo = resultSet.getLong("codigo");
                    String nome = resultSet.getString("nome");
                    String iniciais = resultSet.getString("iniciais");

                
                    Autor autor = new Autor(codigo, nome, iniciais);
                    listaAutores.add(autor);
                }

            resultSet.close();
            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return listaAutores;
    }

}

