import enums.Statistics;
import enums.TransferStatus;
import org.testng.annotations.*;
import org.testng.Assert;

public class FootballClubTest extends TestBase {


    @Test
    public void getFootballersListAsStringTest() {
        //no players in the club
        Assert.assertEquals(TransferMarkt.interMilan.getFootballersListAsString(), "No Players in the club");

        //have players in the club
        Assert.assertEquals(TransferMarkt.bayernMunchen.getFootballersListAsString(), "1. Robert Lewandowski\n2. Thomas Muller\n");

        //check after adding 1 more player
        TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.bale, 0);
        Assert.assertEquals(TransferMarkt.bayernMunchen.getFootballersListAsString(), "1. Robert Lewandowski\n2. Thomas Muller\n3. Gareth Bale\n");

        //check after removing a player
        TransferMarkt.bayernMunchen.terminateContract(TransferMarkt.bale);
        Assert.assertEquals(TransferMarkt.bayernMunchen.getFootballersListAsString(), "1. Robert Lewandowski\n2. Thomas Muller\n");
    }

    @Test
    public void buyFootballerTest() {
        //buy a free agent offering 0
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.bale, 0), TransferStatus.SUCCESSFUL);
        Assert.assertTrue(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.bale));
        Assert.assertEquals(TransferMarkt.bale.getCurrentClubName(), TransferMarkt.bayernMunchen.getClubName());

        //buy a free agent offering more than 0
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.mkhitaryan, 20_000_000), TransferStatus.FREE_TRANSFER);
        Assert.assertTrue(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.mkhitaryan));
        Assert.assertEquals(TransferMarkt.mkhitaryan.getCurrentClubName(), TransferMarkt.bayernMunchen.getClubName());

        //buy a player that's already in the club
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.bale, 0), TransferStatus.ALREADY_IN_CLUB);
        Assert.assertTrue(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.bale));
        Assert.assertEquals(TransferMarkt.bale.getCurrentClubName(), TransferMarkt.bayernMunchen.getClubName());

        //offer too small price for a player
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.pique, 10_000_000), TransferStatus.OFFER_TOO_SMALL);
        Assert.assertFalse(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.pique));
        Assert.assertEquals(TransferMarkt.pique.getCurrentClubName(), TransferMarkt.barcelona.getClubName());

        //offer too big price for a player
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.pique, 80_000_000), TransferStatus.OFFER_TOO_BIG);
        Assert.assertTrue(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.pique));
        Assert.assertFalse(TransferMarkt.barcelona.getFootballersList().contains(TransferMarkt.pique));
        Assert.assertEquals(TransferMarkt.pique.getCurrentClubName(), TransferMarkt.bayernMunchen.getClubName());
        Assert.assertEquals(TransferMarkt.bayernMunchen.getBudget(), 6.0E7);

        //no enough budget
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.benzema, 60_000_000), TransferStatus.NO_ENOUGH_BUDGET);
        Assert.assertFalse(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.benzema));
        Assert.assertTrue(TransferMarkt.realMadrid.getFootballersList().contains(TransferMarkt.benzema));

        //complete a normal transfer
        Assert.assertEquals(TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.laporte, 45_000_000), TransferStatus.SUCCESSFUL);
        Assert.assertTrue(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.laporte));
        Assert.assertFalse(TransferMarkt.manchesterCity.getFootballersList().contains(TransferMarkt.laporte));
    }

    @Test
    public void terminateContractTest() {
        //remove player from his club
        TransferMarkt.bayernMunchen.terminateContract(TransferMarkt.lewandowski);
        Assert.assertFalse(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.lewandowski));
        Assert.assertTrue(TransferMarkt.lewandowski.getCurrentClubName().isEmpty());

        //try to remove player not from current club
        TransferMarkt.bayernMunchen.terminateContract(TransferMarkt.pique);
        Assert.assertFalse(TransferMarkt.bayernMunchen.getFootballersList().contains(TransferMarkt.pique));
        Assert.assertEquals(TransferMarkt.pique.getCurrentClubName(), TransferMarkt.barcelona.getClubName());
    }

    @Test
    public void getClubStatisticsTest() {
        //small club, but good striker
        Assert.assertEquals(TransferMarkt.bayernMunchen.getClubStatistics(), Statistics.GOOD_STRIKER);

        //small club, but 2 good strikers
        TransferMarkt.bayernMunchen.terminateContract(TransferMarkt.muller);
        TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.benzema, 80_000_000);
        Assert.assertEquals(TransferMarkt.bayernMunchen.getClubStatistics(), Statistics.OMG_2_STRIKERS);

        //empty club
        Assert.assertEquals(TransferMarkt.interMilan.getClubStatistics(), Statistics.EMPTY_CLUB);

        //small club
        Assert.assertEquals(TransferMarkt.manchesterCity.getClubStatistics(), Statistics.SMALL_CLUB);

        //normal club
        TransferMarkt.bayernMunchen.buyFootballer(TransferMarkt.bale, 0);
        Assert.assertEquals(TransferMarkt.bayernMunchen.getClubStatistics(), Statistics.YOURE_COOL);
    }
}
