package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class MenuPersonController {
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
    private Button btnMenu;
    @FXML
    int zakazId = (int) (Math.random()*100);

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

    }

    @FXML
    private void handleMenu() {
        Users user = new Users();

        boolean flag;

        flag = user.checkAdm();

        if (flag) {
            btnMenu.getScene().getWindow().hide();

            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("MainMenu.fxml"));

            try {
                load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = load.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setContentText("Вы не администратор!");
            alert.showAndWait();
        }
    }

    @FXML
    public void addTovar() {
            int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();

            System.out.println(zakazId);

            Products selectedProduct = catalogTable.getSelectionModel().getSelectedItem();
            Users user = new Users();
            String login = user.getLogin();

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Поместить в корзину?");
            alert2.setHeaderText("Вы уверены, что хотите поместить товар в корзину?");
            Optional<ButtonType> option = alert2.showAndWait();

            if (selectedIndex >= 0 && (option.get() == ButtonType.OK)) {
                String selProd = zakazId + " " + login;
                Busket.writeInBusket(selProd);
                String selProd2 = zakazId + " " + selectedProduct.toString();
                System.out.println(selProd2);
                Busket.writeInBusketInfo(selProd2);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(null);
                alert.setTitle("Не выделено");
                alert.setHeaderText("Не выбран товар");
                alert.setContentText("Выберите товар в таблице");
                alert.showAndWait();
            }
    }
}

