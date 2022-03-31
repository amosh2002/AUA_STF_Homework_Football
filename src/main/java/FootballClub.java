import java.util.ArrayList;

public class FootballClub {
    private String clubName;
    private String country;
    private ArrayList<Footballer> footballers;

    public FootballClub() {
        this.footballers = new ArrayList<>();
        this.footballers.add(new Footballer("Leo", "Messi", 65000000));
    }

    public ArrayList<Footballer> getFootballersList() {
        return footballers;
    }

}
