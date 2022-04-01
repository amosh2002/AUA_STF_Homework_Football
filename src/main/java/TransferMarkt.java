import java.util.ArrayList;

public class TransferMarkt {

    public static FootballClub realMadrid, barcelona, bayernMunchen, manchesterCity, interMilan;
    public static Footballer benzema, lewandowski, alaba, bale, muller, pique, busquets, laporte, mkhitaryan;
    public static ArrayList<FootballClub> footballClubsList;

    public static void completeTransfer(FootballClub currentClub, Footballer footballer, double offerSum) {
        currentClub.setBudget(currentClub.getBudget() - offerSum);
        if (offerSum != 0) {
            for (FootballClub footballClub : footballClubsList) {
                if (footballer.getCurrentClubName().equals(footballClub.getClubName())) {
                    footballClub.terminateContract(footballer);
                }
            }
        }
        currentClub.getFootballersList().add(footballer);
        footballer.setCurrentClubName(currentClub.getClubName());


    }
}
