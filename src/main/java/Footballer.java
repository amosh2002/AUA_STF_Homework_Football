import lombok.Data;

@Data
public class Footballer {
    private String firstName;
    private String lastName;
    private double price;
    private String currentClubName;

    public Footballer(String firstName, String lastName, double price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
    }
}
