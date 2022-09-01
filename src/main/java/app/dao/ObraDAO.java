package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import app.Domain.PacoteObras.Editora;
import app.Domain.PacoteObras.Autor;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.PacoteObras.Obra;


public class ObraDAO extends GenericDAO{

    private CategoriaObraDAO catdao;
    private CopiaDAO copdao;
    private EditoraDAO edao;
    private AutorDAO adao;
    

    public void insert(Obra obra){
        String sql = "INSERT INTO Obra (isbn, titulo, categoria, palavraChave, dataPublicacao, Edicao, editora_id, numPaginas) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, obra.getIsbn());
            statement.setString(2, obra.getTitulo());
            statement.setInt(3, obra.getCategoria().getCodigo());
        
            String keywords = "";
            for (String palavra : obra.getPalavrasChave()) {
                keywords = keywords + palavra + ";";
            }
            statement.setString(4, keywords);

            java.sql.Date mySQLDate = new java.sql.Date(obra.getDataPublicacao().getTime());
            statement.setDate(5, mySQLDate);
            statement.setString(6, obra.getEdicao());
            statement.setLong(7, obra.getEditora().getId());
            statement.setInt(8, obra.getNumeroPaginas());

            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Obra obra) {
        String sql = "UPDATE Obra SET isbn = ?, titulo = ?, categoria = ?, palavraChave = ?, dataPublicacao = ?, Edicao = ?, editora_id = ?, numPaginas = ? WHERE codigo = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);


            statement.setLong(1, obra.getIsbn());
            statement.setString(2, obra.getTitulo());
            statement.setInt(3, obra.getCategoria().getCodigo());

            String keywords = "";
            for (String palavra : obra.getPalavrasChave()) {
                keywords = keywords + palavra + ";";
            }
            statement.setString(4, keywords);

            java.sql.Date mySQLDate = new java.sql.Date(obra.getDataPublicacao().getTime());
            statement.setDate(5, mySQLDate);
            statement.setString(6, obra.getEdicao());
            statement.setLong(7, obra.getEditora().getId());
            statement.setInt(8, obra.getNumeroPaginas());

            statement.setLong(9, obra.getCodigo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Obra obra){
        String sql = "DELETE FROM Obra where codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, obra.getCodigo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Obra getByCodigo(Long codigo){
        Obra obra = null;

        String sql = "SELECT * from Obra WHERE codigo = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long isbn = resultSet.getLong("isbn");
                CategoriaObra categoria = catdao.getByCodigo(resultSet.getInt("categoria"));
                List<Autor> lista = adao.getAllByObraId(codigo);
                
                List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavraChave").split(";")));
                Date dataPublicacao = resultSet.getDate("dataPublicacao");
                String Edicao = resultSet.getString("Edicao");
                Editora editora = edao.getById(resultSet.getLong("editora_id"));
                String titulo = resultSet.getString("titulo");
                int numPaginas = resultSet.getInt("numPaginas");

                obra = new Obra(codigo, isbn, categoria, lista, palavrasChave, dataPublicacao, Edicao, editora, titulo, numPaginas);
                
                
                
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return obra;

    }

    public Obra getByIsbn(Long isbn){
        Obra obra = null;

        String sql = "SELECT * from Obra WHERE isbn = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long codigo = resultSet.getLong("codigo");
                CategoriaObra categoria = catdao.getByCodigo(resultSet.getInt("categoria"));
                List<Autor> lista = adao.getAllByObraId(codigo);

                List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavraChave").split(";")));
                Date dataPublicacao = resultSet.getDate("dataPublicacao");
                String Edicao = resultSet.getString("Edicao");
                Editora editora = edao.getById(resultSet.getLong("editora_id"));
                String titulo = resultSet.getString("titulo");
                int numPaginas = resultSet.getInt("numPaginas");

                obra = new Obra(codigo, isbn, categoria, lista, palavrasChave, dataPublicacao, Edicao, editora, titulo, numPaginas);



            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return obra;

    }

}


