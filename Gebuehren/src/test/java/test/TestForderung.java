package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.EntitätMitÜbergängen;
import bebucht.ÜbergangsGruppe;
import buchung.Konto;

public class TestForderung extends EntitätMitÜbergängen {
    public enum Art {
        FORDERUNG, GUTSCHRIFT
    }

    public enum Status {
        INIT, AKTIV, STORNIERT
    }

    public static final Konto rest = new TestKonto(1, "Rest");
    public static final Konto haben = new TestKonto(2, "Haben");
    public static final Konto storniert = new TestKonto(4, "Storniert");

    public TestForderung(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, Status.INIT);
        addBetrag(haben, betrag);
        buchen(repository, Buchungsarten.ANLAGE, Buchungsarten.ANLAGE.name()
                + " angelegt", betrag);
    }

    @Override
    public ÜbergangsGruppe getMöglicheÜbergänge() {
        return new ÜbergangsGruppe()
                .addÜbergang(Buchungsarten.STORNIEREN, Status.AKTIV,
                        Status.STORNIERT, rest, storniert)
                .addÜbergang(Buchungsarten.ANLAGE, Status.INIT, Status.AKTIV,
                        haben, rest)
                .addÜbergang(Buchungsarten.BEZAHLEN, Status.AKTIV, Status.AKTIV,
                        rest, haben);
    }

    public MonetaryAmount getSoll() {
        return getBetrag();
    }

    public MonetaryAmount getHaben() {
        return getBetrag(haben);
    }

    public MonetaryAmount getRest() {
        return getBetrag(rest);
    }

    public MonetaryAmount getStorniert() {
        return getBetrag(storniert);
    }

    public void bezahlen(BuchungsRepository repository,MonetaryAmount betrag){
        buchen(repository, Buchungsarten.BEZAHLEN, Buchungsarten.BEZAHLEN.name()
                + " angelegt", betrag);
        
    }
    
    public void bezahlen(BuchungsRepository repository,TestZahlungsEingang zahlungseingang,MonetaryAmount betrag){
        buchen(repository, zahlungseingang,Buchungsarten.BEZAHLEN, Buchungsarten.BEZAHLEN.name()
                + " angelegt", betrag);
        
    }
}
