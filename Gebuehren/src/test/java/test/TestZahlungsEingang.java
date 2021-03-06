package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.EntitätMitÜbergängen;
import bebucht.ÜbergangsGruppe;
import buchung.Konto;

public class TestZahlungsEingang extends EntitätMitÜbergängen {
    public enum Art {
        LASTSCHRIFT, GUTSCHRIFT
    }

    public enum Status {
        INIT, AKTIV, STORNIERT
    }

    public static final Konto offen= new TestKonto(1, "Offen");
    public static final Konto zugewiesen = new TestKonto(2, "Zugewiesen");
    public static final Konto storniert = new TestKonto(4, "Storniert");

    public TestZahlungsEingang(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, Status.INIT);
        addBetrag(zugewiesen, betrag);
        buchen(repository, Buchungsarten.ANLAGE, Buchungsarten.ANLAGE.name()
                + " angelegt", betrag);
    }

    @Override
    public ÜbergangsGruppe getMöglicheÜbergänge() {
        return new ÜbergangsGruppe()
                .addÜbergang(Buchungsarten.STORNIEREN, Status.AKTIV,
                        Status.STORNIERT, offen, storniert)
                .addÜbergang(Buchungsarten.ANLAGE, Status.INIT, Status.AKTIV,
                        zugewiesen, offen)
                .addÜbergang(Buchungsarten.BEZAHLEN, Status.AKTIV, Status.AKTIV,
                        zugewiesen,offen);
    }

    public MonetaryAmount getZugewiesen() {
        return getBetrag(zugewiesen);
    }

    public MonetaryAmount getOffen() {
        return getBetrag(offen);
    }

    public MonetaryAmount getStorniert() {
        return getBetrag(storniert);
    }

    public void bezahlen(BuchungsRepository repository,MonetaryAmount betrag){
        buchen(repository, Buchungsarten.BEZAHLEN, Buchungsarten.BEZAHLEN.name()
                + " angelegt", betrag);
        
    }
}
