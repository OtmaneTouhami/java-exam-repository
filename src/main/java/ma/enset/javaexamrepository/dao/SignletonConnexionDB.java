package ma.enset.javaexamrepository.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignletonConnexionDB {
    private static SignletonConnexionDB instance;
    private final Connection connection;

    private SignletonConnexionDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database credentials
            String url = "jdbc:mysql://localhost:3306/exam_db";
            String username = "root";
            String password = "Slme@1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    public static SignletonConnexionDB getInstance() throws SQLException {
        if (instance == null) {
            return new SignletonConnexionDB();
        } else if (instance.getConnection().isClosed()) {
            return new SignletonConnexionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}