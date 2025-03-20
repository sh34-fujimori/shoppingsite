package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/webshop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "5356";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
