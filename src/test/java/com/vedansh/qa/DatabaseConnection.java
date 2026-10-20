
package com.vedansh.qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ecommerce_test";

    private static final String USER = "root";

    public static Connection getConnection() throws SQLException {
        String password = System.getenv("ECOMMERCE_DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable ECOMMERCE_DB_PASSWORD is not set."
            );
        }

        return DriverManager.getConnection(URL, USER, password);
    }
}
