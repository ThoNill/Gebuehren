package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.WerteQuelle;
import beans.Konto;
import beans.Werte;

public class Überzahlung implements WerteQuelle{
    private Repository repository;
    private Konto überzahlungsKonto;
    private Enum<?> überzahlungsArt;
    
    public Überzahlung(Repository repository,Enum überzahlungsArt,Konto überzahlungsKonto) {
        this.repository = repository;
        this.überzahlungsKonto = überzahlungsKonto;
        this.überzahlungsArt = überzahlungsArt;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        MonetaryAmount alteÜberzahlung = getAlteÜbezahlung(abrechnung);
        MonetaryAmount aktuellesSaldo = repository.saldo(abrechnung);
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alteÜberzahlung);
        Werte w = new Werte();
        if (reduziertesSaldo.isNegative()) {
            w.put(überzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlteÜbezahlung(Abrechnung abrechnung) {
        Werte alteBuchung = repository.getAktuelleWerte(überzahlungsArt, abrechnung);
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
