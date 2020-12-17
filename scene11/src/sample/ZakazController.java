package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;


public class ZakazController {
    @FXML
    private final ObservableList<BusketData> busketData = FXCollections.observableArrayList();

    @FXML
    private final ObservableList<BusketInfo> busketDataInfo = FXCollections.observableArrayList();

    @FXML
    private TableView<BusketData> catalogTable;

    @FXML
    private TableView<BusketInfo> catalogTable2;

    @FXML
    private TableColumn<BusketData, String> login;
    @FXML
    private TableColumn<BusketInfo, String> productName;
    @FXML
    private TableColumn<BusketInfo, String> productSum;
    @FXML
    private TableColumn<BusketInfo, String> productCount;
    @FXML
    private TableColumn<BusketData, String> productZakaz;
    @FXML
    private Label finallySum;
    @FXML
    private Label productZakazLabel;
    @FXML
    private ComboBox comboBox;
    @FXML
    private Hyperlink hyper;

    ObservableList<String> boxList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        productZakazLabel.setText("");
        finallySum.setText("");
        comboBox.setDisable(true);
        busketData.clear();

        boxList.add("Оплачено");
        boxList.add("Доставленно");
        comboBox.setItems(boxList);

        setBusketDataTable();
        productZakaz.setCellValueFactory(new PropertyValueFactory<>("productZakaz"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        catalogTable.setItems(busketData);

        catalogTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> showProductDetails(newValue));
    }

    private void showProductDetails(BusketData newValue) {
        if (newValue != null) {
            busketDataInfo.clear();
            productZakazLabel.setText(newValue.getProductZakaz().toString());
            String zakaz = newValue.getProductZakaz().toString();
            showBusketInfoTable(zakaz);
            hyperLock();
        } else {
            productZakazLabel.setText("");
            finallySum.setText("");
        }
    }

    private void showBusketInfoTable(String zakaz) {
        String line;
        Double allSum = 0.0;
        File file = new File("src/sample/busketInfo.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (zakaz.equals(data[0])) {
                    allSum += Double.valueOf(data[2]);
                    busketDataInfo.add(new BusketInfo(line));
                    productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                    productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
                    productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
                    catalogTable2.setItems(busketDataInfo);
                }
                finallySum.setText(Double.toString(allSum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBusketDataTable() {

        String line;
        File file = new File("src/sample/busket.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                busketData.add(new BusketData(line));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hyperUnlock() {
        comboBox.setDisable(false);
    }

    public void hyperLock() {
        comboBox.setDisable(true);
    }
}
