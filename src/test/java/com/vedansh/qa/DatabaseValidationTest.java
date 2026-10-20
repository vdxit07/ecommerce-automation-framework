
package com.vedansh.qa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseValidationTest {

    @Test
    public void shouldValidateProductPriceInDatabase() throws Exception {

        String sql =
                "SELECT product_name, price FROM products WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 101);

            try (ResultSet resultSet = statement.executeQuery()) {

                Assert.assertTrue(
                        resultSet.next(),
                        "Product ID 101 should exist"
                );

                Assert.assertEquals(
                        resultSet.getString("product_name"),
                        "Laptop"
                );

                Assert.assertEquals(
                        resultSet.getBigDecimal("price").toPlainString(),
                        "55000.00"
                );

                System.out.println(
                        "Database validation passed: Laptop price is 55000.00"
                );
            }
        }
    }
}
