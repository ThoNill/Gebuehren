package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.WerteQuelle;
import beans.Konto;
import beans.Werte;

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
    public Werte getWerte(Abrechnung abrechnung) {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung(abrechnung);
        Werte w = new Werte();
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
