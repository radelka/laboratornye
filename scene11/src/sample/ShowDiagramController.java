package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ShowDiagramController {
    @FXML
    private BarChart barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList productNames = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        String[] products = {"Телевизор", "Комп", "Кресло", "Пуфик"};

        productNames.addAll(Arrays.asList(products));

        xAxis.setCategories(productNames);
        setProductData();
    }

    public void setProductData() {
        int[] productCounter = new int[productNames.size()];

        String line;
        File file = new File("src/sample/BusketInfo.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                for (int i = 0; i < productCounter.length; i++) {
                    if (productNames.get(i).equals(data[1])) {
                        productCounter[i] += 1;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < productCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(productNames.get(i), productCounter[i]));
        }

        barChart.getData().add(series);
    }
}
