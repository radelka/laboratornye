package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

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
    void initialize() {
        String line;
        File file = new File("src/sample/products.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                productData.add(new Products(line));
                productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
                productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
                productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
                catalogTable.setItems(productData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        showProductDetails(null);

        catalogTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> showProductDetails(newValue));
    }

    @FXML
    private void showProductDetails(Products product) {
        if (product != null) {
            productIdLabel.setText(product.getProductId().toString());
            productNameLabel.setText(product.getProductName());
            productSumLabel.setText(product.getProductSum().toString());
            productCountLabel.setText(product.getProductCount().toString());
        } else {
            productIdLabel.setText("");
            productNameLabel.setText("");
            productSumLabel.setText("");
            productCountLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteProduct() {
        int selectedIndex=catalogTable.getSelectionModel().getSelectedIndex();

        Products selectedProduct = catalogTable.getSelectionModel().getSelectedItem();

        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Удаление товара");
        alert2.setHeaderText("Вы уверены, что хотите удалить товар?");
        Optional<ButtonType> option = alert2.showAndWait();

        if (selectedIndex >= 0 && (option.get() == ButtonType.OK)) {
            catalogTable.getItems().remove(selectedIndex);
            Products.writeDelete(selectedProduct);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран товар");
            alert.setContentText("Выберите товар в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    public boolean showProductEditDialog(Products product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditSceneController.class.getResource(("add.fxml")));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditSceneController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        }   catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewProduct() {

        Products tempProduct = new Products(0,"",0.0,0);

        boolean okClicked = this.showProductEditDialog(tempProduct);
        if (okClicked) {
            productData.add(tempProduct);
            Products.writeAdd(tempProduct);
        }
    }

    @FXML
    private void handleEditProduct() {
        try {
            Products selectedProduct = catalogTable.getSelectionModel().getSelectedItem();
            Products firstProduct = selectedProduct.clone();
            int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
            if (selectedProduct != null && selectedIndex >= 0) {
                boolean okClicked = showProductEditDialog(selectedProduct);
                if (okClicked) {
                    productData.set(selectedIndex, selectedProduct);
                    Products.writeEdit(selectedProduct, firstProduct);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(null);
                alert.setTitle("Не выделено");
                alert.setHeaderText("Не выбран товар");
                alert.setContentText("Выберите товар в таблице");
                alert.showAndWait();
            }
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }
}
