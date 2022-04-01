import enums.Country;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Arrays;

public class TestBase {

    @BeforeClass
    public void createClubsAndPlayers() {
        TransferMarkt.realMadrid = new FootballClub("Real Madrid", Country.SPAIN);
        TransferMarkt.barcelona = new FootballClub("Barcelona", Country.SPAIN);
        TransferMarkt.bayernMunchen = new FootballClub("Bayern Munchen", Country.GERMANY);
        TransferMarkt.manchesterCity = new FootballClub("Manchester City", Country.UNITED_KINGDOM);
        TransferMarkt.interMilan = new FootballClub("Inter Milan", Country.ITALY);

        TransferMarkt.footballClubsList = new ArrayList<>(Arrays.asList(TransferMarkt.realMadrid, TransferMarkt.barcelona, TransferMarkt.bayernMunchen, TransferMarkt.manchesterCity, TransferMarkt.interMilan));

        TransferMarkt.benzema = new Footballer("Karim", "Benzema", 60_000_000);
        TransferMarkt.lewandowski = new Footballer("Robert", "Lewandowski", 70_000_000);
        TransferMarkt.alaba = new Footballer("David", "Alaba", 50_000_000);
        TransferMarkt.bale = new Footballer("Gareth", "Bale", 15_000_000);
        TransferMarkt.muller = new Footballer("Thomas", "Muller", 40_000_000);
        TransferMarkt.pique = new Footballer("Gerard", "Pique", 20_000_000);
        TransferMarkt.busquets = new Footballer("Sergio", "Busquets", 20_000_000);
        TransferMarkt.laporte = new Footballer("Aymeric", "Laporte", 45_000_000);
        TransferMarkt.mkhitaryan = new Footballer("Henrikh", "Mkhitaryan", 40_000_000);
    }

    @BeforeMethod
    public void prepareClubs() {
        TransferMarkt.realMadrid.setFootballersList(new ArrayList<>(Arrays.asList(TransferMarkt.benzema, TransferMarkt.alaba, TransferMarkt.bale)));
        TransferMarkt.barcelona.setFootballersList(new ArrayList<>(Arrays.asList(TransferMarkt.pique, TransferMarkt.busquets)));
        TransferMarkt.bayernMunchen.setFootballersList(new ArrayList<>(Arrays.asList(TransferMarkt.lewandowski, TransferMarkt.muller)));
        TransferMarkt.manchesterCity.setFootballersList(new ArrayList<>(Arrays.asList(TransferMarkt.laporte)));
        //leave interMilan empty

        TransferMarkt.benzema.setCurrentClubName(TransferMarkt.realMadrid.getClubName());
        TransferMarkt.lewandowski.setCurrentClubName(TransferMarkt.bayernMunchen.getClubName());
        TransferMarkt.alaba.setCurrentClubName(TransferMarkt.realMadrid.getClubName());
        TransferMarkt.muller.setCurrentClubName(TransferMarkt.bayernMunchen.getClubName());
        TransferMarkt.pique.setCurrentClubName(TransferMarkt.barcelona.getClubName());
        TransferMarkt.busquets.setCurrentClubName(TransferMarkt.barcelona.getClubName());
        TransferMarkt.laporte.setCurrentClubName(TransferMarkt.manchesterCity.getClubName());
        //leave bale and mkhitaryan without clubs;


    }

    @AfterMethod
    public void cleanClubsAndPlayers() {
        TransferMarkt.realMadrid.clearClub();
        TransferMarkt.barcelona.clearClub();
        TransferMarkt.bayernMunchen.clearClub();
        TransferMarkt.manchesterCity.clearClub();
        TransferMarkt.interMilan.clearClub();

        TransferMarkt.benzema.setCurrentClubName(null);
        TransferMarkt.lewandowski.setCurrentClubName(null);
        TransferMarkt.alaba.setCurrentClubName(null);
        TransferMarkt.bale.setCurrentClubName(null);
        TransferMarkt.muller.setCurrentClubName(null);
        TransferMarkt.pique.setCurrentClubName(null);
        TransferMarkt.busquets.setCurrentClubName(null);
        TransferMarkt.laporte.setCurrentClubName(null);
        TransferMarkt.mkhitaryan.setCurrentClubName(null);
    }
}
