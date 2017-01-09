package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;
import bebucht.‹bergangsGruppe;
import buchung.Konto;

public class TestForderung extends Entit‰tMit‹berg‰ngen {
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
    public ‹bergangsGruppe getMˆgliche‹berg‰nge() {
        return new ‹bergangsGruppe()
                .add‹bergang(Buchungsarten.STORNIEREN, Status.AKTIV,
                        Status.STORNIERT, rest, storniert)
                .add‹bergang(Buchungsarten.ANLAGE, Status.INIT, Status.AKTIV,
                        haben, rest)
                .add‹bergang(Buchungsarten.BEZAHLEN, Status.AKTIV, Status.AKTIV,
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
