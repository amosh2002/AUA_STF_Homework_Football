import org.testng.annotations.*;
import org.testng.Assert;

public class FootballClubTest {
    @Test
    public void getFootballersListTest() {
        Assert.assertNotNull(new FootballClub().getFootballersList());
    }
}
