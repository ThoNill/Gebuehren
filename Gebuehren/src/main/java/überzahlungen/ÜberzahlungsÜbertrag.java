package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.Konto;

public class ÜberzahlungsÜbertrag extends Überzahlung {

    private Konto übertragsKonto;
    private Enum<?> übertragsArt;

    public ÜberzahlungsÜbertrag(Enum<?> überzahlungsArt,
            Konto überzahlungsKonto, Enum<?> übertragsArt, Konto übertragsKonto,Abrechnung abrechnung) {
        super(überzahlungsArt,  überzahlungsKonto,abrechnung);
        this.übertragsKonto = übertragsKonto;
        this.übertragsArt = übertragsArt;
    }

    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return super.getRelevanteAbrechnung().nächsteAbrechnung();
    }

    @Override
    public Bewegungen getBewegungen() {
        MonetaryAmount alteÜberzahlung = getAlteÜbezahlung();
        Bewegungen w = new Bewegungen();
        if (!alteÜberzahlung.isZero()) {
            w.put(übertragsKonto, alteÜberzahlung.negate());
        }
        return w;
    }

    @Override
    public Enum<?> getArt() {
        return übertragsArt;
    }

    @Override
    public String getBuchungsText() {
        return "Übertrag Überzahlungen";
    }

}
