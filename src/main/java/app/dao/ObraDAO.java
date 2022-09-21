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
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.PacoteObras.Obra;


public class ObraDAO extends GenericDAO{

    private CategoriaObraDAO catdao;
    private EditoraDAO edao;

    public ObraDAO(){
        this.catdao = new CategoriaObraDAO();
        this.edao = new EditoraDAO();
    }
    

    public void insert(Obra obra) throws SQLException{
        String sql = "INSERT INTO Obra (isbn, titulo, categoriaObra_id, palavrasChave, dataPublicacao, edicao, editora_id, numPaginas, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, obra.getIsbn());
        statement.setString(2, obra.getTitulo());
        statement.setLong(3, obra.getCategoria().getCodigo());
    
        String keywords = "";
        for (String palavra : obra.getPalavrasChave()) {
            keywords = keywords + palavra + ";";
        }
        statement.setString(4, keywords);

        java.sql.Date mySQLDate = new java.sql.Date(obra.getDataPublicacao().getTime());
        statement.setDate(5, mySQLDate);
        statement.setString(6, obra.getEdicao());
        statement.setLong(7, obra.getEditora().getId());
        statement.setInt(8, obra.getNumPaginas());
        statement.setString(9, "DISPONIVEL");

        statement.executeUpdate();

        statement.close();
        conn.close();
        
    }

    public void update(Obra obra) throws SQLException{
        String sql = "UPDATE Obra SET isbn = ?, titulo = ?, categoriaObra_id = ?, palavrasChave = ?, dataPublicacao = ?, edicao = ?, editora_id = ?, numPaginas = ?, status = ? WHERE codigo = ?";
        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);


        statement.setLong(1, obra.getIsbn());
        statement.setString(2, obra.getTitulo());
        statement.setLong(3, obra.getCategoria().getCodigo());

        String keywords = "";
        for (String palavra : obra.getPalavrasChave()) {
            keywords = keywords + palavra + ";";
        }
        statement.setString(4, keywords);

        java.sql.Date mySQLDate = new java.sql.Date(obra.getDataPublicacao().getTime());
        statement.setDate(5, mySQLDate);
        statement.setString(6, obra.getEdicao());
        statement.setLong(7, obra.getEditora().getId());
        statement.setInt(8, obra.getNumPaginas());
        statement.setString(9, obra.getStatus());

        statement.setLong(9, obra.getCodigo());
        statement.executeUpdate();
        statement.close();
        conn.close();
        
    }


    public void delete(Obra obra) throws SQLException{
        String sql = "DELETE FROM Obra where codigo = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, obra.getCodigo());
        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    public Obra getByCodigo(Long codigo) throws SQLException{
        Obra obra = null;

        String sql = "SELECT * from Obra WHERE codigo = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, codigo);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Long isbn = resultSet.getLong("isbn");
            String titulo = resultSet.getString("titulo");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");

            obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status);
            
            
            
        }
        resultSet.close();
        statement.close();
        conn.close();
        return obra;

    }

    public Obra getByIsbn(Long isbn) throws SQLException{
        Obra obra = null; 

        String sql = "SELECT * from Obra WHERE isbn = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, isbn);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            Long codigo = resultSet.getLong("codigo");
            String titulo = resultSet.getString("titulo");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));                
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");

            obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return obra;
    }

    public List<Obra> getAllByIsbn(Long isbn) throws SQLException{
        List<Obra> listaObras = new ArrayList<>();

        String sql = "SELECT * from Obra WHERE isbn = ?";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, isbn);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Long codigo = resultSet.getLong("codigo");
            String titulo = resultSet.getString("titulo");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));                
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");

            Obra obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status); 
            listaObras.add(obra);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return listaObras;
    }

    public Obra getByTitulo(String titulo) throws SQLException{
        Obra obra = null;

        String sql = "SELECT * from Obra WHERE titulo = ?";
   
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, titulo);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Long codigo = resultSet.getLong("codigo");
            Long isbn = resultSet.getLong("isbn");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));                
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");

            obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status); 
        }
        resultSet.close();
        statement.close();
        conn.close();
        
        return obra;
    }

    public List<Obra> getAllByKeyWord(String palavra) throws SQLException{
        List<Obra> listaObras = new ArrayList<>();

        String sql = "SELECT * from Obra";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Long codigo = resultSet.getLong("codigo");
            Long isbn = resultSet.getLong("isbn");
            String titulo = resultSet.getString("titulo");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));                
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");
            
            if(palavrasChave.contains(palavra)){
                Obra obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status); 
                listaObras.add(obra);
            }
        }
        resultSet.close();
        statement.close();
        conn.close();
        
        return listaObras;
    }

    public List<Obra> getAll() throws SQLException{
        List<Obra> listaObras = new ArrayList<>();

        String sql = "SELECT * from Obra";

        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Long codigo = resultSet.getLong("codigo");
            Long isbn = resultSet.getLong("isbn");
            String titulo = resultSet.getString("titulo");
            CategoriaObra categoria = catdao.getByCodigo(resultSet.getLong("categoriaObra_id"));                
            List<String> palavrasChave = new ArrayList<String>(Arrays.asList(resultSet.getString("palavrasChave").split(";")));
            Date dataPublicacao = resultSet.getDate("dataPublicacao");
            String edicao = resultSet.getString("edicao");
            Editora editora = edao.getById(resultSet.getLong("editora_id"));
            int numPaginas = resultSet.getInt("numPaginas");
            String status = resultSet.getString("status");
            
            Obra obra = new Obra(codigo, isbn, categoria, palavrasChave, dataPublicacao, edicao, editora, titulo, numPaginas, status); 
            listaObras.add(obra);
            
        }
        resultSet.close();
        statement.close();
        conn.close();
        
        return listaObras;
    }

}


