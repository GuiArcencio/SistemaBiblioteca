package app.dao;

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

    protected Connection getConnection() throws SQLException{ 
        String url = System.getenv("CLEARDB_DATABASE_URL");
        String user = System.getenv("MYSQL_USER");
        String pass = System.getenv("MYSQL_PASS");

        return DriverManager.getConnection(url, user, pass);
    }
}
