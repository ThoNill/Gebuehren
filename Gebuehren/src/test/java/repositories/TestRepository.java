package repositories;

import geb�hren.MarkierendesRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

public class TestRepository implements  AktuelleWerteRepository,MarkierendesRepository {

    private Konto mwst = new TestKonto(1, "Mwst");
    private Bewegungen aktuelleWerte;

    public TestRepository() {
        super();
        aktuelleWerte = new Bewegungen();
    }

    
    @Override
    public Bewegungen getAktuelleWerte(Enum art,Abrechnung abrechnung) {
        return aktuelleWerte;
    }

    public void setAktuelleWerte(Bewegungen aktuelleWerte) {
        this.aktuelleWerte = aktuelleWerte;
    }
    
    @Override
    public void insertBuchung(BuchungsAuftrag auftrag) {
    }


    @Override
    public void markieren(Abrechnung abrechnung) {
    }

}