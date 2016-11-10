package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import buchung.Bewegungen;
import buchung.Konto;

public class ÜberzahlungsÜbertrag extends Überzahlung {

    private Konto übertragsKonto;
    private Enum<?> übertragsArt;

    public ÜberzahlungsÜbertrag(Repository repository, Enum<?> überzahlungsArt,
            Konto überzahlungsKonto, Enum<?> übertragsArt, Konto übertragsKonto) {
        super(repository,überzahlungsArt,  überzahlungsKonto);
        this.übertragsKonto = übertragsKonto;
        this.übertragsArt = übertragsArt;
    }

    @Override
    public Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
        return abrechnung.nächsteAbrechnung();
    }

    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        MonetaryAmount alteÜberzahlung = getAlteÜbezahlung(abrechnung);
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
