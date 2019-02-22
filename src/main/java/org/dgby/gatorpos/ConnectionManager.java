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

    public static void executeQuery(String query, ThrowingConsumer<ResultSet, SQLException> callback)
            throws SQLException {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);) {
            callback.accept(resultSet);
        }
    }

    public static int executeUpdate(String query) throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            return statement.executeUpdate(query);
        }
    }

    public static Integer insertRow(String tableName, String[] columns, Object[] data) throws SQLException {
        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, String.join(", ", columns),
                String.join(", ", Collections.nCopies(data.length, "?")));

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);) {
            int i = 1;
            for (Object obj : data) {
                if (obj instanceof String)
                    preparedStatement.setString(i, (String) obj);
                else if (obj instanceof Integer)
                    preparedStatement.setInt(i, (Integer) obj);
                else
                    preparedStatement.setObject(i, obj);

                i++;
            }

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                    if (resultSet.next())
                        return resultSet.getInt("last_insert_rowid()");
                }
            }

            return -1;
        }
    }

    public static void createTable(String tableName, String[] columns) {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, String.join(", ", columns));

        try {
            executeUpdate(query);
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }
}