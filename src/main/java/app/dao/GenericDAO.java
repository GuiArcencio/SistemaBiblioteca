package app.dao;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

abstract public class GenericDAO {
    public GenericDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        try{
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
        
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
}
