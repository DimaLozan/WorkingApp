package com.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Manage your products app");
        primaryStage.setScene(new Scene(root));

        DatabaseHandler.createProductsTable("newDatabase.db");
        DatabaseHandler.createUsersTable("newDatabase.db");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}