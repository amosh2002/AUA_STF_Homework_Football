package enums;

public enum TransferStatus {

    SUCCESSFUL("Transfer successfully completed!"),
    FREE_TRANSFER("Transfer successfully completed, and the full offer is returned, as the player was without a club!"),
    OFFER_TOO_BIG("Transfer successfully completed, and some part of the offer is returned, as the player was cheaper!"),
    OFFER_TOO_SMALL("Transfer was rejected because of small amount of offer!"),
    NO_ENOUGH_BUDGET("Transfer was rejected because the club does not have that much funds"),
    ALREADY_IN_CLUB("The player is already in the club"),
    WRONG_OFFER("The offer price is less than 0.0");


    private String status;

    TransferStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
