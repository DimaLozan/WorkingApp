package com.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

enum AlertBoxType {
    NAME_INPUT_ERROR,
    PRICE_INPUT_ERROR,
    QUANTITY_INPUT_ERROR
}

public class AlertBox {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button okBtn;

    @FXML
    private Label errorMsg;

    @FXML
    private void okBtnAction() {
        Stage stage = (Stage)okBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

    }

    public static void displayErrorMessage(AlertBoxType alertBoxType) {
        Stage window = new Stage();
        Parent ui = null;

        try {
            switch(alertBoxType) {
                case NAME_INPUT_ERROR:
                    ui = FXMLLoader.load(AlertBox.class.getClassLoader().getResource("nameAlertWindow.fxml"));
                    break;
                case PRICE_INPUT_ERROR:
                    ui = FXMLLoader.load(AlertBox.class.getClassLoader().getResource("priceAlertWindow.fxml"));
                    break;
                case QUANTITY_INPUT_ERROR:
                    ui = FXMLLoader.load(AlertBox.class.getClassLoader().getResource("quantityAlertWindow.fxml"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        window.setScene(new Scene(ui));
        window.showAndWait();
    }
}