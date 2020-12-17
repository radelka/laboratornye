package sample;

import javafx.fxml.FXML;

import java.io.*;
import java.util.ArrayList;

public class Busket {
    public String login;
    public Integer productId;
    public String productName;
    public Double productSum;
    public Integer productCount;

    public Busket(String login, Integer productId, String productName, Double productSum, Integer productCount) {
        this.login = login;
        this.productId=productId;
        this.productName=productName;
        this.productSum=productSum;
        this.productCount=productCount;
    }

    public Busket(String line) {
        String[] data = line.split(" ");
        this.login = data[0];
        this.productId = Integer.valueOf(data[1]);
        this.productName = data[2];
        this.productSum = Double.valueOf(data[3]);
        this.productCount = Integer.valueOf(data[4]);
    }
        @FXML
    public static void writeInBusket(String selectedProduct) {
        File file = new File("src/sample/busket.txt");

        try (FileWriter writer = new FileWriter(file, true)) {
            StringBuilder data = new StringBuilder();
            data.append(selectedProduct + "\n");
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
