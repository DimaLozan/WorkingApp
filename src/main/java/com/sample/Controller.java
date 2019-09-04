package com.sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> tableColumnName;

    @FXML
    private TableColumn<Product, Float> tableColumnPrice;

    @FXML
    private TableColumn<Product, Integer> tableColumnQuantity;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button deleteBtn;



    private ObservableList<Product> products;

    @FXML
    void initialize() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        products = FXCollections.observableArrayList();
        tableView.setItems(DatabaseHandler.getProductsFromDatabase(products));

        // TODO: Fix the alert boxes if's when the users doesn't enters this or that field
        addBtn.setOnAction(event -> {
            String name = getStringFromTextField(nameTextField);
            float price = getFloatFromTextField(priceTextField);
            int quantity = getIntFromTextField(quantityTextField);

            if (name == "" && price == 0 && quantity == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.NO_DATA_INPUT_ERROR);
            } else if (name == "") {
                AlertBox.displayErrorMessage(AlertBoxType.NAME_INPUT_ERROR);
            } else if (price == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.PRICE_INPUT_ERROR);
            } else if (quantity == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.QUANTITY_INPUT_ERROR);
            } else if (name == "" && price == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.NAME_INPUT_ERROR);
                AlertBox.displayErrorMessage(AlertBoxType.PRICE_INPUT_ERROR);
            } else if (name == "" && quantity == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.NAME_INPUT_ERROR);
                AlertBox.displayErrorMessage(AlertBoxType.QUANTITY_INPUT_ERROR);
            } else if (price == 0 && quantity == 0) {
                AlertBox.displayErrorMessage(AlertBoxType.PRICE_INPUT_ERROR);
                AlertBox.displayErrorMessage(AlertBoxType.QUANTITY_INPUT_ERROR);
            } else {
                addItem(products, name, price, quantity);
            }
        });

        deleteBtn.setOnAction(event -> {
            Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                tableView.getItems().remove(selectedProduct);
                DatabaseHandler.removeProduct(selectedProduct.getName());
            } else {

            }
        });
    }

    private String getStringFromTextField(TextField nameTextField) {
        if (nameTextField.getText().length() != 0) {
            return nameTextField.getText();
        } else {
            return "";
        }

    }

    private float getFloatFromTextField(TextField priceTextField) {
        if (priceTextField.getText().length() != 0) {
            return Float.parseFloat(priceTextField.getText());
        } else {
            return 0;
        }
    }

    private int getIntFromTextField(TextField quantityTextField) {
        if (quantityTextField.getText().length() != 0) {
            return Integer.parseInt(quantityTextField.getText());
        } else {
            return 0;
        }
    }

    private void addItem(ObservableList<Product> products, String name, float price, int quantity) {
        if (price != 0 && quantity != 0 && name.length() != 0) {
            products.add(new Product(name, price, quantity));
            DatabaseHandler.addProduct(new Product(name, price, quantity));
        }
    }

}