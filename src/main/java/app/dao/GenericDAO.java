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
        String url = "jdbc:mysql://localhost:3306/Biblioteca";

        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("MYSQL_USER");
        String pass = dotenv.get("MYSQL_PASS");

        return DriverManager.getConnection(url, user, pass);
    }
}
