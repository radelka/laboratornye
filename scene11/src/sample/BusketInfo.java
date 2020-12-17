package sample;

public class BusketInfo {

    Integer productZakaz;
    String productName;
    Integer productCount;
    Double productSum;

    public BusketInfo(String line) {
        String[] data = line.split(" ");
        this.productZakaz = Integer.valueOf(data[0]);
        this.productName = data[1];
        this.productSum = Double.valueOf(data[2]);
        this.productCount = Integer.valueOf(data[3]);
    }

    public Integer getProductZakaz() { return productZakaz; }

    public void setProductZakaz(Integer productZakaz) { this.productZakaz = productZakaz; }

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public Integer getProductCount() {return productCount;}
    public void setProductCount(Integer productCount) { this.productCount = productCount; }

    public Double getProductSum() { return productSum; }
    public void setProductSum(Double productSum) { this.productSum = productSum; }
}
