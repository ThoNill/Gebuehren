package test;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import org.junit.internal.runners.model.EachTestNotifier;

import beans.Konto;
import bebucht.BebuchteEntit‰t;
import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;
import bebucht.‹bergang;
import bebucht.‹bergangsGruppe;

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

    public static Konto init = new TestKonto(10, "Init");
    public static Konto soll = new TestKonto(20, "Soll");
    public static Konto storniert = new TestKonto(30, "Storniert");

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
