package beans;




public class BuchungsAuftrag extends BuchungsartUndText {
    private Werte werte;
    
    public BuchungsAuftrag(Enum art,String buchungsText,
            Werte werte) {
        super(art,buchungsText);
        this.werte = werte;
    }

    public Werte getWerte() {
        return werte;
    }

}
