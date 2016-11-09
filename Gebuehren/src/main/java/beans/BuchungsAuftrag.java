package beans;




public class BuchungsAuftrag extends BuchungsartUndText {
    private Bewegungen bewegungen;
    
    public BuchungsAuftrag(Enum<?> art,String buchungsText,
            Bewegungen bewegungen) {
        super(art,buchungsText);
        this.bewegungen = bewegungen;
    }

    public Bewegungen getWerte() {
        return bewegungen;
    }

}
