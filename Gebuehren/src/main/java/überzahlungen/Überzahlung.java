package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.WerteQuelle;
import beans.Konto;
import beans.Werte;

public class �berzahlung implements WerteQuelle{
    private Repository repository;
    private Konto �berzahlungsKonto;
    private Enum<?> �berzahlungsArt;
    
    public �berzahlung(Repository repository,Enum �berzahlungsArt,Konto �berzahlungsKonto) {
        this.repository = repository;
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung(abrechnung);
        MonetaryAmount aktuellesSaldo = repository.saldo(abrechnung);
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alte�berzahlung);
        Werte w = new Werte();
        if (reduziertesSaldo.isNegative()) {
            w.put(�berzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlte�bezahlung(Abrechnung abrechnung) {
        Werte alteBuchung = repository.getAktuelleWerte(�berzahlungsArt, abrechnung);
        return alteBuchung.get(�berzahlungsKonto);
    }

    @Override
    public Enum<?> getArt() {
        return �berzahlungsArt;
    }

    @Override
    public String getBuchungsText() {
        return "�berzahlungen";
    }
    
  

}
