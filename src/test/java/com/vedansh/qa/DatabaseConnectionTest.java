
package com.vedansh.qa;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseConnectionTest {

    @Test
    public void shouldConnectToMySQLDatabase() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Assert.assertNotNull(connection, "Database connection should not be null");
            Assert.assertFalse(connection.isClosed(), "Database connection should be open");

            System.out.println("MySQL database connection successful!");
            System.out.println("Connected database: " +
                    connection.getCatalog());
        }
    }
}
