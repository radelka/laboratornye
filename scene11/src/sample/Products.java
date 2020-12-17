package sample;

import javafx.fxml.FXML;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Products implements Cloneable {
    String productName;
    Integer productId, productCount;
    Double productSum;


    public Products(Integer productId, String productName, Double productSum, Integer productCount) {
        this.productId=productId;
        this.productName=productName;
        this.productSum=productSum;
        this.productCount=productCount;
    }

    public Products(String line) {
        String[] data = line.split(" ");
        this.productId = Integer.valueOf(data[0]);
        this.productName = data[1];
        this.productSum = Double.valueOf(data[2]);
        this.productCount = Integer.valueOf(data[3]);
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName=productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId=productId;
    }

    public Double getProductSum() {
        return productSum;
    }

    public void setProductSum(Double productSum) {
        this.productSum=productSum;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount=productCount;
    }

    public String toString() {
        return productId + " " + productName + " " + productSum + " " + productCount;
    }

    public static void writeDelete(Products product) {
        ArrayList<Products> products = new ArrayList<>();
        String line;

        File file = new File("src/sample/products.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                Products tempProduct = new Products(line);
                products.add(tempProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder data = new StringBuilder();
            for (Products s : products) {
                if (!s.equals(product)) {
                    data.append(s.toString() + "\n");
                }
            }
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public static void writeAdd(Products product) {
        File file = new File("src/sample/products.txt");
        try (FileWriter writer = new FileWriter(file,true)) {
            StringBuilder data = new StringBuilder();
            data.append(product.toString()+"\n");
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public static void writeEdit(Products selectedProduct, Products firstProduct) {
        ArrayList<Products> products = new ArrayList<>();
        String line;
        File file = new File("src/sample/products.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = reader.readLine()) != null) {
                Products tempProduct = new Products(line);
                System.out.println(line);
                products.add(tempProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder data = new StringBuilder();
            for (Products s : products) {
                if (s.equals(firstProduct)) {
                    data.append(selectedProduct.toString() + "\n");
                } else {
                    data.append(s.toString() + "\n");
                }
            }
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products product = (Products) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productSum, product.productSum) &&
                Objects.equals(productCount, product.productCount);
    }

    @Override
    public Products clone() throws CloneNotSupportedException {
        return (Products) super.clone();
    }
}
