package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;
import bebucht.‹bergangsGruppe;
import buchung.Konto;

public class BebuchtTestEntit‰t extends Entit‰tMit‹berg‰ngen {
    public enum Art {
        AUFTRAG, LIEFERSCHEIN, RECHNUNG, GUTSCHRIFT
    }

    public enum Status {
        INIT, ANGELEGT, GEBUCHT, STORNIERT
    }

    public enum Buchungsart {
        ANLAGE, STORNIEREN
    }

    public static final Konto init = new TestKonto(10, "Init");
    public static final Konto soll = new TestKonto(20, "Soll");
    public static final Konto storniert = new TestKonto(30, "Storniert");

    public BebuchtTestEntit‰t(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, Status.INIT);
        addBetrag(init, betrag);
        buchen(repository, Buchungsart.ANLAGE, Buchungsart.ANLAGE.name()
                + " angelegt", betrag);
    }

    @Override
    public ‹bergangsGruppe getMˆgliche‹berg‰nge() {
        return new ‹bergangsGruppe().add‹bergang(Buchungsart.STORNIEREN,
                Status.ANGELEGT, Status.STORNIERT, soll, storniert)
                .add‹bergang(Buchungsart.ANLAGE, Status.INIT, Status.ANGELEGT,
                        init, soll);
    }

    public MonetaryAmount getSoll() {
        return getBetrag(soll);
    }

    public MonetaryAmount getStorniert() {
        return getBetrag(storniert);
    }

}
