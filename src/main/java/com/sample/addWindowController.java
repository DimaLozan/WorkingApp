package com.sample;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField quantityTextField;

    private ObservableList<Product> products;

    @FXML
    private void addBtnAction() {

    }

    @FXML
    private void cancelBtnAction() {
        Stage addWindow = (Stage)cancelBtn.getScene().getWindow();
        addWindow.close();
    }

    @FXML
    void initialize() {

    }
}

