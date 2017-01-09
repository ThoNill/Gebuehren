package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;
import bebucht.‹bergangsGruppe;
import buchung.Konto;

public class TestZahlungsEingang extends Entit‰tMit‹berg‰ngen {
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
    public ‹bergangsGruppe getMˆgliche‹berg‰nge() {
        return new ‹bergangsGruppe()
                .add‹bergang(Buchungsarten.STORNIEREN, Status.AKTIV,
                        Status.STORNIERT, offen, storniert)
                .add‹bergang(Buchungsarten.ANLAGE, Status.INIT, Status.AKTIV,
                        zugewiesen, offen)
                .add‹bergang(Buchungsarten.BEZAHLEN, Status.AKTIV, Status.AKTIV,
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
