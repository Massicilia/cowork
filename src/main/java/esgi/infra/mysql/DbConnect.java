package esgi.infra.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

    public static java.sql.Connection connection = null;

    public static java.sql.Connection getConnection() {
        String url = System.getenv("DB_NAME");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, username, password);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static void closeConnection(java.sql.Connection connection) {
        try {
            connection.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
