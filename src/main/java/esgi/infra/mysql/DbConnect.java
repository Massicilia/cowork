package esgi.infra.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

    public static java.sql.Connection connection = null;

    public static java.sql.Connection getConnection() {
        String url = "jdbc:"+System.getenv( "JDBC_DATABSE_URL");//"jdbc:mysql://localhost:3306/cowork?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //String url = "jdbc:mysql://localhost:3306/cowork?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = System.getenv("JDBC_DATABSE_USERNAME");
        //String username = "root";
        String password = System.getenv("JDBC_DATABSE_PASSWORD");
        //String password = "root";
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
