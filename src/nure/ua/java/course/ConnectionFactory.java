package nure.ua.java.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String dbHost = "localhost:3306";
    private static final String dbName = "lab2java";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";

    private static String dbUrl = "jdbc:mysql://" + dbHost + "/" + dbName;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
            throw new SQLException("Connection failed...");
        }
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        connection.setAutoCommit(false);
        return connection;
    }
}
