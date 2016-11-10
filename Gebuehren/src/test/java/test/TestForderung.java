package test;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import org.junit.internal.runners.model.EachTestNotifier;

import abrechnung.Repository;
import bebucht.BebuchteEntität;
import bebucht.BuchungsRepository;
import bebucht.EntitätMitÜbergängen;
import bebucht.Übergang;
import bebucht.ÜbergangsGruppe;
import buchung.Konto;

public class TestForderung extends EntitätMitÜbergängen {
    public enum Art {
        FORDERUNG, GUTSCHRIFT
    }

    public enum Status {
        INIT, AKTIV, STORNIERT
    }

    public enum Buchungsart {
        ANLAGE, BEZAHLEN, STORNIEREN
    }

    public static Konto rest = new TestKonto(1, "Rest");
    public static Konto haben = new TestKonto(2, "Haben");
    public static Konto storniert = new TestKonto(4, "Storniert");

    public TestForderung(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, Status.INIT);
        addBetrag(haben, betrag);
        buchen(repository, Buchungsart.ANLAGE, Buchungsart.ANLAGE.name()
                + " angelegt", betrag);
    }

    @Override
    public ÜbergangsGruppe getMöglicheÜbergänge() {
        return new ÜbergangsGruppe()
                .addÜbergang(Buchungsart.STORNIEREN, Status.AKTIV,
                        Status.STORNIERT, rest, storniert)
                .addÜbergang(Buchungsart.ANLAGE, Status.INIT, Status.AKTIV,
                        haben, rest)
                .addÜbergang(Buchungsart.BEZAHLEN, Status.AKTIV, Status.AKTIV,
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
        buchen(repository, Buchungsart.BEZAHLEN, Buchungsart.BEZAHLEN.name()
                + " angelegt", betrag);
        
    }
}
