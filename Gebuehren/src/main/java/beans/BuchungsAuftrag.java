package beans;




public class BuchungsAuftrag {
    private int art;
    private String buchungsText;
    private Werte werte;
    
    public BuchungsAuftrag(int art,String buchungsText,
            Werte werte) {
        super();
        this.art = art;
        this.buchungsText = buchungsText;
        this.werte = werte;
    }

    public int getArt() {
        return art;
    }

  

    public String getBuchungsText() {
        return buchungsText;
    }

    public Werte getWerte() {
        return werte;
    }

}
