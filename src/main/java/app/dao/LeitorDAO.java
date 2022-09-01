package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.CategoriaLeitor;
import app.Domain.PacoteUsuarios.Usuario;


public class LeitorDAO extends GenericDAO {

    private UsuarioDAO dao;

    public void init() {
        dao = new UsuarioDAO();
    }   
    
    public void insert(Usuario usuario, Leitor leitor){

        dao.insert(usuario);
        String sql = "INSERT INTO Leitor (idUsuario, email, documentoId, grupoAcademico, categoria) VALUES (?, ?, ?, ?, ?) ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            Long idUsuario = usuario.getId();
            statement.setLong(1, idUsuario);
            statement.setString(2, leitor.getEmail());
            statement.setString(3, leitor.getDocumentoId());
            statement.setBoolean(4, leitor.getGrupoAcademico());
            statement.setLong(5, leitor.getCategoria().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario, Leitor leitor) {

        dao.update(usuario);
        String sql = "UPDATE Leitor SET email = ?, documentoId = ?, grupoAcademico = ?, categoria_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, leitor.getEmail());
            statement.setString(2, leitor.getDocumentoId());
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

    public void delete(Usuario usuario, Leitor leitor){

        dao.delete(usuario);
        String sql = "DELETE FROM Leitor where idUsuario = ?";

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


    public Usuario getById(Long id){

        Leitor leitor = null;

        String sql = "SELECT * from Leitor WHERE idUsuario = ?";

        try{   
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String email = resultSet.getString("email");
                String documentoId = resultSet.getString("documentoId");
                boolean grupoAcademico = resultSet.getBoolean("grupoAcademico");

                Long categoriaId = resultSet.getLong("id");
                CategoriaLeitor categoria = new CategoriaLeitorDAO().getById(categoriaId);


                leitor = new Leitor(id, email, documentoId, grupoAcademico, categoria);
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        Usuario leitorF = new Leitor(dao.getById(id), leitor);

        return leitorF;

    }
}
