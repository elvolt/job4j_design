package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    private final Properties prs = new Properties();

    public void load(InputStream io) {
        try {
            prs.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return prs.getProperty(key);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        ConnectionDemo con = new ConnectionDemo();
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            con.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = con.getValue("url");
        String login = con.getValue("login");
        String password = con.getValue("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
