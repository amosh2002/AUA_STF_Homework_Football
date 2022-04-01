import enums.Country;
import enums.Statistics;
import enums.TransferStatus;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class FootballClub implements Cloneable {
    private String clubName;
    private Country country;
    private double budget;
    private ArrayList<Footballer> footballersList;

    public FootballClub(String clubName, Country country) {
        this.clubName = clubName;
        this.country = country;
        this.budget = 100_000_000;
        this.footballersList = new ArrayList<>();
    }


    public void clearClub() {
        this.budget = 100_000_000;
        this.footballersList = new ArrayList<>();
    }


    public String getFootballersListAsString() {
        if (footballersList.size() == 0) {
            return "No Players in the club";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Footballer footballer : footballersList) {
            sb.append(i++)
                    .append(". ")
                    .append(footballer.getFirstName())
                    .append(" ")
                    .append(footballer.getLastName())
                    .append("\n");
        }
        return sb.toString();
    }

    public TransferStatus buyFootballer(@NonNull Footballer footballer, double offerSum) {
        if (offerSum < 0) {
            return TransferStatus.WRONG_OFFER;
        }
        if (footballer.getCurrentClubName() == null) {
            TransferMarkt.completeTransfer(this, footballer, 0);
            if (offerSum > 0) {
                return TransferStatus.FREE_TRANSFER;
            } else if (offerSum == 0) {
                return TransferStatus.SUCCESSFUL;
            }
        }
        if (footballer.getCurrentClubName().equals(this.clubName)) {
            return TransferStatus.ALREADY_IN_CLUB;
        }
        if (this.budget - offerSum < 1_000_000) {
            return TransferStatus.NO_ENOUGH_BUDGET;
        }
        if (offerSum > footballer.getPrice() * 2) {
            TransferMarkt.completeTransfer(this, footballer, footballer.getPrice() * 2);
            return TransferStatus.OFFER_TOO_BIG;
        }

        if (offerSum < footballer.getPrice()) {
            return TransferStatus.OFFER_TOO_SMALL;
        }

        TransferMarkt.completeTransfer(this, footballer, offerSum);
        return TransferStatus.SUCCESSFUL;
    }

    public void terminateContract(@NonNull Footballer footballer) {
        if (!footballer.getCurrentClubName().equals(this.clubName)) {
            return;
        }
        footballersList.remove(footballer);
        footballer.setCurrentClubName("");
    }

    public Statistics getClubStatistics() {
        if (getFootballersList().size() < 3) {
            if (getFootballersList().contains(TransferMarkt.lewandowski) || getFootballersList().contains(TransferMarkt.benzema)) {
                if (getFootballersList().contains(TransferMarkt.lewandowski) && getFootballersList().contains(TransferMarkt.benzema)) {
                    return Statistics.OMG_2_STRIKERS;
                }
                return Statistics.GOOD_STRIKER;
            }
            if (getFootballersList().size() >= 1) {
                return Statistics.SMALL_CLUB;
            }
            if (getFootballersList().size() == 0) {
                return Statistics.EMPTY_CLUB;
            }
        }
        return Statistics.YOURE_COOL;
    }


}
