public class Car {
    public String name;
    private String price;
    private String engineType;
    private String enginePower;
    public int maxSpeed;
    public Car(String name) {
        this.name = name;
        this.price = "3500000";
        this.engineType = "V8";
        this.enginePower = "339";
        this.maxSpeed = 230;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Car name is: " + name + " and max speed = " + maxSpeed;
    }
}
