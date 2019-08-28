package com.sample;

import javafx.collections.ObservableList;

import javax.swing.filechooser.FileSystemView;
import java.sql.*;

public final class DatabaseHandler {

    private static String getDatabaseURL(String databaseName) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String url = null;

        if (System.getProperty("os.name").equals("Linux")) {
            url = "jdbc:sqlite:" + fsv.getDefaultDirectory() + "/Documents/" + databaseName;
        } else {
            url = "jdbc:sqlite:" + fsv.getDefaultDirectory() + "\\" + databaseName;
        }

        return url;
    }

    private static Connection connect() {
        String url = getDatabaseURL("newDatabase.db");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    // TODO: Add Mac support
    public static void createDatabase(String filename) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String url = null;

        if (System.getProperty("os.name").equals("Linux")){
            url = "jdbc:sqlite:" + fsv.getDefaultDirectory() + "/Documents/" + filename;
        } else {
            url = "jfbc:sqlite:" + fsv.getDefaultDirectory() + "\\" + filename;
        }

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Database successfully created");
            } else {
                System.out.println("Database cannot be created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // TODO: Add Mac support
    public static void createUsersTable(String databaseName) {
        String url = getDatabaseURL(databaseName);

        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " username text NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createProductsTable(String databaseName) {
        String url = getDatabaseURL(databaseName);

        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + " id integer PRIMARY KEY, \n"
                + " productName text NOT NULL, \n"
                + " price real NOT NULL, \n"
                + " quantity integer NOT NULL\n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static ObservableList<Product> getProductsFromDatabase(ObservableList<Product> products) {
        String sql = "SELECT * FROM products";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(resultSet.getString("productName"), resultSet.getFloat("price"), resultSet.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    public static void addProduct(Product product) {
        String sql = "INSERT INTO products(productName, price, quantity) VALUES(?,?,?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeProduct(String name) {
        String sql = "DELETE FROM products WHERE productName = ?";

        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Product findProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";

        String name = "";
        float price = 0;
        int quantity = 0;

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString("productName");
                price = resultSet.getFloat("price");
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Product(name, price, quantity);
    }

}