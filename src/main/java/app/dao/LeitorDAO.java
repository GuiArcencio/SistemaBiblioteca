package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.CategoriaLeitor;
import app.Domain.PacoteUsuarios.Usuario;


public class LeitorDAO extends GenericDAO {

    private UsuarioDAO dao;

    public void init() {
        dao = new UsuarioDAO();
    }   
    
    public void insert(Leitor leitor){
        Usuario usuario = new Leitor(leitor.getNome(), leitor.getTelefone(), leitor.getDataNascimento(), leitor.getEndereco());
        dao.insert(usuario);
        String sql = "INSERT INTO leitor (idUsuario, email, documentoId, categoria_id, grupoAcademico) VALUES (?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            Long idUsuario = usuario.getId();
            statement.setLong(1, idUsuario);
            statement.setString(2, leitor.getEmail());
            statement.setLong(3, leitor.getDocumentoId());
            statement.setBoolean(4, leitor.getGrupoAcademico());
            statement.setLong(5, leitor.getCategoria().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Leitor leitor) {
        Usuario usuario = new Leitor(leitor.getNome(), leitor.getTelefone(), leitor.getDataNascimento(), leitor.getEndereco());
        dao.update(usuario);
        String sql = "UPDATE leitor SET email = ?, documentoId = ?, grupoAcademico = ?, categoria_id = ? WHERE idUsuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, leitor.getEmail());
            statement.setLong(2, leitor.getDocumentoId());
            statement.setBoolean(3, leitor.getGrupoAcademico());
            statement.setLong(4, leitor.getCategoria().getId());
            statement.setLong(5, leitor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Leitor leitor){
        Usuario usuario = new Leitor(leitor.getNome(), leitor.getTelefone(), leitor.getDataNascimento(), leitor.getEndereco());

        dao.delete(usuario);
        String sql = "DELETE FROM leitor where idUsuario = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, leitor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Leitor getById(Long id){

        Leitor leitor = null;

        String sql = "SELECT * from leitor WHERE idUsuario = ?";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String email = resultSet.getString("email");
                Long documentoId = resultSet.getLong("documentoId");
                boolean grupoAcademico = resultSet.getBoolean("grupoAcademico");

                Long categoriaId = resultSet.getLong("categoria_id");
                CategoriaLeitor categoria = new CategoriaLeitorDAO().getById(categoriaId);


                leitor = new Leitor(id, email, documentoId, grupoAcademico, categoria);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return leitor;

    }

    public Leitor getByDocumentId(Long documentoId) throws SQLException{

        Leitor leitor = null;

        String sql = "SELECT * from leitor WHERE documentoId = ?";

        
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, documentoId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String email = resultSet.getString("email");
            Long idUsuario = resultSet.getLong("idUsuario");
            boolean grupoAcademico = resultSet.getBoolean("grupoAcademico");

            Long categoriaId = resultSet.getLong("categoria_id");
            CategoriaLeitor categoria = new CategoriaLeitorDAO().getById(categoriaId);


            leitor = new Leitor(idUsuario, email, documentoId, grupoAcademico, categoria);
        }
        resultSet.close();
        statement.close();
        conn.close();
       
        return leitor;
    }

    public List<Leitor> getAll() throws SQLException{
        List<Leitor> leitores = new ArrayList<>();
        String sql = "SELECT * FROM leitor";
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long idUsuario = resultSet.getLong("idUsuario");
            String email = resultSet.getString("email");
            Long documentoId = resultSet.getLong("documentoId");
            Long categoriaId = resultSet.getLong("categoria_id");
            CategoriaLeitor categoria = new CategoriaLeitorDAO().getById(categoriaId);
            Boolean grupoAcademico = resultSet.getBoolean("grupoAcademico");

            Leitor leitor = new Leitor(idUsuario, email, documentoId, grupoAcademico, categoria);
            leitores.add(leitor);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return leitores;
    }

}
