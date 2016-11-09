package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.BewegungenQuelle;
import beans.Konto;
import beans.Bewegungen;

public class Überzahlung implements BewegungenQuelle{
    private Repository repository;
    private Konto überzahlungsKonto;
    private Enum<?> überzahlungsArt;
    
    public Überzahlung(Repository repository,Enum überzahlungsArt,Konto überzahlungsKonto) {
        this.repository = repository;
        this.überzahlungsKonto = überzahlungsKonto;
        this.überzahlungsArt = überzahlungsArt;
    }

    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        MonetaryAmount alteÜberzahlung = getAlteÜbezahlung(abrechnung);
        MonetaryAmount aktuellesSaldo = repository.saldo(abrechnung);
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alteÜberzahlung);
        Bewegungen w = new Bewegungen();
        if (reduziertesSaldo.isNegative()) {
            w.put(überzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlteÜbezahlung(Abrechnung abrechnung) {
        Bewegungen alteBuchung = repository.getAktuelleWerte(überzahlungsArt, abrechnung);
        return alteBuchung.get(überzahlungsKonto);
    }

    @Override
    public Enum<?> getArt() {
        return überzahlungsArt;
    }

    @Override
    public String getBuchungsText() {
        return "Überzahlungen";
    }
    
  

}
