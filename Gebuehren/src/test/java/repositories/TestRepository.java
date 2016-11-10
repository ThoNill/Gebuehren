package repositories;

import gebühren.MarkierendesRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import abrechnung.Repository;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

public class TestRepository implements Repository, AktuelleWerteRepository,MarkierendesRepository {

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
    public void insertBuchung(Abrechnung abrechnung, BuchungsAuftrag auftrag) {
    }


    @Override
    public void markieren(Abrechnung abrechnung) {
    }

}