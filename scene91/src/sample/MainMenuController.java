package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab catalogTab;
    @FXML
    private Tab catalogTab2;

    @FXML
    void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Catalog.fxml"));
        catalogTab.setContent(loader.load());
    }

    @FXML
    private void selectCatalogTab() {
        tabPane.getSelectionModel().select(catalogTab);
    }

    @FXML
    private void selectCatalogTab2() {
        tabPane.getSelectionModel().select(catalogTab2);
    }

    @FXML
    private void catalogInWindow() {

        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("Catalog.fxml"));

        try {
            load.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = load.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void selectInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("");
        alert.setContentText("Программа учебная. Версия 1.0");
        alert.showAndWait();
    }
}
