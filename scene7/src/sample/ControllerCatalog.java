package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

public class ControllerCatalog {

        @FXML
    private final ObservableList<Products> productData = FXCollections.observableArrayList();

        @FXML
    private TableView<Products> catalogTable;
        @FXML
    private TableColumn<Products, String> productId;
        @FXML
    private TableColumn<Products, String> productName;
        @FXML
    private TableColumn<Products, String> productSum;
        @FXML
    private TableColumn<Products, String> productCount;
        @FXML
    private Label productIdLabel;
        @FXML
    private Label productNameLabel;
        @FXML
    private Label productSumLabel;
        @FXML
    private Label productCountLabel;

        @FXML
    private Button btnAdd;

        @FXML
    private Button btnEdit;

        @FXML
    private Button btnDell;

        @FXML
    void initialize() {
        productData.add(new Products(1, "Chair", 1500.00, 2));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
        productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
        catalogTable.setItems(productData);

        showProductDetails(null);

        catalogTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> showProductDetails(newValue));
    }

        @FXML
    private void showProductDetails(Products product) {
            if (product != null) {
                productIdLabel.setText(product.getProductId().toString());
                productNameLabel.setText(product.getProductName().toString());
                productSumLabel.setText(product.getProductSum().toString());
                productCountLabel.setText(product.getProductCount().toString());
            } else {
                productIdLabel.setText("");
                productNameLabel.setText("");
                productSumLabel.setText("");
                productCountLabel.setText("");
            }
        }

}
