package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ControllerReg {

    @FXML
    private Button btn1;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private Button btn2;

    @FXML
    void initialize() {
        btn1.setOnAction(event -> {
            try {
                Path p = Paths.get("src/sample/users.txt");

                String login = textField1.getText();
                String psw = textField2.getText();

                boolean flag = checkLog(login);

                if (flag) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("!");
                    alert.setContentText("Такой логин уже существует!");
                    alert.showAndWait();
                } else {
                    String newUser = System.lineSeparator() + login + " " + psw;
                    try {
                        Files.write(p, newUser.getBytes(), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setHeaderText("Здравстуйте, " + login + "!");
                    alert.setContentText("Вы успешно зарегистрировались в нашем интернет-магазине. Удачных покупок!");
                    alert.showAndWait();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        btn2.setOnAction(event -> {
            btn2.getScene().getWindow().hide();

            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("sample.fxml"));

            try {
                load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = load.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    public boolean checkLog(String log) throws FileNotFoundException {
        File file = new File("src/sample/users.txt");
        boolean flag = false;
        String tookStr = log;
        String str = "";

        try (Scanner sc = new Scanner(file)) {
            do {
                do {
                    str = str + sc.next();
                } while (sc.hasNext(" "));

                if (str.equals(tookStr)) {
                    flag = true;
                }
                str = "";
            } while (sc.hasNextLine());
            return flag;
        }
    }
}
