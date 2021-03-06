package test;

import javax.money.MonetaryAmount;

import bebucht.BuchungsRepository;
import bebucht.EntitätMitÜbergängen;
import bebucht.ÜbergangsGruppe;
import buchung.Konto;

public class BebuchtTestEntität extends EntitätMitÜbergängen {
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

    public BebuchtTestEntität(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, Status.INIT);
        addBetrag(init, betrag);
        buchen(repository, Buchungsart.ANLAGE, Buchungsart.ANLAGE.name()
                + " angelegt", betrag);
    }

    @Override
    public ÜbergangsGruppe getMöglicheÜbergänge() {
        return new ÜbergangsGruppe().addÜbergang(Buchungsart.STORNIEREN,
                Status.ANGELEGT, Status.STORNIERT, soll, storniert)
                .addÜbergang(Buchungsart.ANLAGE, Status.INIT, Status.ANGELEGT,
                        init, soll);
    }

    public MonetaryAmount getSoll() {
        return getBetrag(soll);
    }

    public MonetaryAmount getStorniert() {
        return getBetrag(storniert);
    }

}
