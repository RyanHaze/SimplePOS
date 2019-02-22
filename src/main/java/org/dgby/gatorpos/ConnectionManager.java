package org.dgby.gatorpos;

import java.sql.*;
import java.util.Collections;

public class ConnectionManager {
    private static String url = "jdbc:sqlite:database.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @FunctionalInterface
    public interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }

    public static void executeQuery(String query, ThrowingConsumer<ResultSet, SQLException> callback) throws SQLException {
        try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            callback.accept(resultSet);
        }
    }

    public static int executeUpdate(String query) throws SQLException {
        try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
        ) {
            return statement.executeUpdate(query);
        }
    }

    public static Integer insertRow(String tableName, String[] columns, Object[] data) throws SQLException {
        String query = "INSERT INTO " + tableName + " ("
                + String.join(", ", columns) + ") VALUES ("
                + String.join(", ", Collections.nCopies(data.length, "?")) + ")";
        
        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            int i = 1;
            for (Object obj : data) {
                if (obj instanceof String)
                    preparedStatement.setString(i, (String)obj);
                else if (obj instanceof Integer)
                    preparedStatement.setInt(i, (Integer)obj);
                else
                    preparedStatement.setObject(i, obj);

                i++;
            }
            
            if (preparedStatement.executeUpdate() > 0) {
                try (
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                ) {
                    if (resultSet.next())
                        return resultSet.getInt("last_insert_rowid()");
                }
            }

            return -1;
        }
    }

    public static int createTable(String tableName, String[] coloums) {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
        for (String coloum : coloums)
            query += coloum + ", ";
        query = query.substring(0, query.length() - 2) + ")";
        
        try {
            return executeUpdate(query);
        } catch (SQLException sqlEx) {
            System.out.println("[SQLITE_ERROR][createTable]: " + query);
            System.out.println(sqlEx.getMessage());
            return -1;
        }
    }
}