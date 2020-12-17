package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;


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
    void initialize() {
        btn1.setOnAction(event -> {
            Users user = new Users();

            user.login = textField1.getText();
            user.password = textField2.getText();

            boolean flag = false;

            try {
                flag = user.checkPsw();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (flag) {
                label.setText("Пароль верный!");
            } else {
                label.setText("Пароль не верный!");
            }
        });
    }
}
