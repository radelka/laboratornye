package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


public class Controller {

    @FXML
    private Button btn1;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private Label label;

    @FXML
    private Button btn2;

    @FXML
    private Hyperlink hyper1;

    @FXML
    void initialize() {
        btn1.setOnAction(event -> {
            Users user = new Users();

            user.login = textField1.getText();
            user.password = textField2.getText();

            user.setLogin(textField1.getText());

            boolean flag = false;

            try {
                flag = user.checkPsw();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (flag) {
                btn1.getScene().getWindow().hide();

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("MenuPerson.fxml"));

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
                label.setText("Пароль не верный!");
            }
        });

        btn2.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Очистка");
            alert.setHeaderText("");
            alert.setContentText("Вы уверены что хотите очистить поля входа?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                textField1.setText("");
                textField2.setText("");
            }
        });

        hyper1.setOnAction(event -> {
            hyper1.getScene().getWindow().hide();

            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("regist.fxml"));

            try {
                load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = load.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}
