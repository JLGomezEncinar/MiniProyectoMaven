package com.iessanalberto.dam1.models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = UsuarioDAO.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");

            }

            // Load properties from input stream
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
    }
}
