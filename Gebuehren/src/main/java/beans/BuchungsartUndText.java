package beans;

public class BuchungsartUndText {

    private Enum<?> art;

    private String buchungsText;

    public BuchungsartUndText(Enum<?> art, String buchungsText) {
        super();
        this.art = art;
        this.buchungsText = buchungsText;
    }

    public BuchungsartUndText() {
        super();
    }

    public Enum<?> getArt() {
        return art;
    }

    public String getBuchungsText() {
        return buchungsText;
    }

}