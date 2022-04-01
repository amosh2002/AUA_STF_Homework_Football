package enums;

public enum Statistics {
    OMG_2_STRIKERS("OMG, 2 best strikers in the world here... damnit!."),
    GOOD_STRIKER("Wow, very good striker found."),
    SMALL_CLUB("The Club is too small."),
    EMPTY_CLUB("The Club is empty."),
    YOURE_COOL("You're cool!");

    private String text;

    Statistics(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
