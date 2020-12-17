package sample;

public class Products {
    String productName;
    Integer productId, productCount;
    Double productSum;


    public Products(Integer productId, String productName, Double productSum, Integer productCount) {
        this.productId=productId;
        this.productName=productName;
        this.productSum=productSum;
        this.productCount=productCount;
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

    public void setProductId(String productName) {
        this.productId=productId;
    }

    public Double getProductSum() {
        return productSum;
    }

    public void setProductSum(String productName) {
        this.productSum=productSum;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(String productName) {
        this.productCount=productCount;
    }
}

