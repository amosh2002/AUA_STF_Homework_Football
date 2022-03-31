public class Footballer {
    private String firstName;
    private String lastName;
    private double price;

    public Footballer(String firstName, String lastName, double price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
    }

    public String getName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public double getPrice() {
        return this.price;
    }
}
