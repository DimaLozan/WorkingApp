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

        addBtn.setOnAction(event -> {
            String name = getStringFromTextField(nameTextField);
            float price = getFloatFromTextField(priceTextField);
            int quantity = getIntFromTextField(quantityTextField);
            addItem(products, name, price, quantity);
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
        if (nameTextField.getText().length() == 0) {
            AlertBox.displayErrorMessage(AlertBoxType.NAME_INPUT_ERROR);
            return "";
        } else {
            return nameTextField.getText();
        }


    }

    private float getFloatFromTextField(TextField priceTextField) {
        float price = 0;

        if (priceTextField.getText().length() == 0) {
            AlertBox.displayErrorMessage(AlertBoxType.PRICE_INPUT_ERROR);
        } else {
            try {
                price = Float.parseFloat(priceTextField.getText());
            } catch (Exception e) {
                AlertBox alertBox = new AlertBox();
                alertBox.displayErrorMessage(AlertBoxType.PRICE_INPUT_ERROR);
            }
        }

        return price;
    }

    private int getIntFromTextField(TextField quantityTextField) {
        int quantity = 0;

        if (quantityTextField.getText().length() == 0) {
            AlertBox.displayErrorMessage(AlertBoxType.QUANTITY_INPUT_ERROR);
        } else {
            try {
                quantity = Integer.parseInt(quantityTextField.getText());
            } catch (Exception e) {
                AlertBox alertBox = new AlertBox();
                alertBox.displayErrorMessage(AlertBoxType.QUANTITY_INPUT_ERROR);
            }
        }

        return quantity;
    }

    private void addItem(ObservableList<Product> products, String name, float price, int quantity) {
        if (price != 0 && quantity != 0 && name.length() != 0) {
            products.add(new Product(name, price, quantity));
            DatabaseHandler.addProduct(new Product(name, price, quantity));
        }
    }

}