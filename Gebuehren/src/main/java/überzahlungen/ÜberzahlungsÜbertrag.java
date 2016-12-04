package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.Konto;

public class �berzahlungs�bertrag extends �berzahlung {

    private Konto �bertragsKonto;
    private Enum<?> �bertragsArt;

    public �berzahlungs�bertrag(Enum<?> �berzahlungsArt,
            Konto �berzahlungsKonto, Enum<?> �bertragsArt, Konto �bertragsKonto,Abrechnung abrechnung) {
        super(�berzahlungsArt,  �berzahlungsKonto,abrechnung);
        this.�bertragsKonto = �bertragsKonto;
        this.�bertragsArt = �bertragsArt;
    }

    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return super.getRelevanteAbrechnung().n�chsteAbrechnung();
    }

    @Override
    public Bewegungen getBewegungen() {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung();
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
