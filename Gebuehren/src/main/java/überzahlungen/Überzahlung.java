package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.BewegungenQuelle;
import beans.Konto;
import beans.Bewegungen;

public class �berzahlung implements BewegungenQuelle{
    private Repository repository;
    private Konto �berzahlungsKonto;
    private Enum<?> �berzahlungsArt;
    
    public �berzahlung(Repository repository,Enum �berzahlungsArt,Konto �berzahlungsKonto) {
        this.repository = repository;
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
    }

    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung(abrechnung);
        MonetaryAmount aktuellesSaldo = repository.saldo(abrechnung);
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alte�berzahlung);
        Bewegungen w = new Bewegungen();
        if (reduziertesSaldo.isNegative()) {
            w.put(�berzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlte�bezahlung(Abrechnung abrechnung) {
        Bewegungen alteBuchung = repository.getAktuelleWerte(�berzahlungsArt, abrechnung);
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
