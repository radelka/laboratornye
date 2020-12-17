package sample;

public class BusketData {
    Integer productZakaz;
    String login;

    public BusketData(Integer productZakaz, String login) {
        this.productZakaz = productZakaz;
        this.login = login;
    }

    public BusketData() {
        productZakaz = 0;
        login = "";
    }

    public BusketData(String line) {
        String[] data = line.split(" ");
        this.productZakaz = Integer.valueOf(data[0]);
        this.login = data[1];
    }

    public Integer getProductZakaz() {return productZakaz;}
    public void setProductZakaz(Integer productZakaz) {this.productZakaz = productZakaz;}

    public String getLogin() {return login;}
    public void setLogin(String login) { this.login = login; }
}
