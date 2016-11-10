package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import buchung.Bewegungen;
import buchung.Konto;

public class �berzahlungs�bertrag extends �berzahlung {

    private Konto �bertragsKonto;
    private Enum<?> �bertragsArt;

    public �berzahlungs�bertrag(Repository repository, Enum<?> �berzahlungsArt,
            Konto �berzahlungsKonto, Enum<?> �bertragsArt, Konto �bertragsKonto) {
        super(repository,�berzahlungsArt,  �berzahlungsKonto);
        this.�bertragsKonto = �bertragsKonto;
        this.�bertragsArt = �bertragsArt;
    }

    @Override
    public Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
        return abrechnung.n�chsteAbrechnung();
    }

    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung(abrechnung);
        Bewegungen w = new Bewegungen();
        if (!alte�berzahlung.isZero()) {
            w.put(�bertragsKonto, alte�berzahlung.negate());
        }
        return w;
    }

    @Override
    public Enum<?> getArt() {
        return �bertragsArt;
    }

    @Override
    public String getBuchungsText() {
        return "�bertrag �berzahlungen";
    }

}
