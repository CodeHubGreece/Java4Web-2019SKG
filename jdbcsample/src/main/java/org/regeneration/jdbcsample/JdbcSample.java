package org.regeneration.jdbcsample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class JdbcSample {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/crm";

    private static final String DB_USER = "argy";
    public static final String DB_PASS = "argy";
    private static Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        connection = getDbConnection();
        System.out.println("args = " + Arrays.deepToString(args));
        String insertEmployeeSql = "insert into customer " +
                "(customer_number, last_name, first_name)" +
                " values " +
                "( ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeSql)) {
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Argyriadis");
            preparedStatement.setString(3, "Chris");

            preparedStatement.executeUpdate();
            System.out.println("Record saved");


        }

    }

    private static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASS);
    }

    private static void closeDbConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
