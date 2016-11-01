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
    
    public Überzahlung(Repository repository,Konto überzahlungsKonto) {
        this.repository = repository;
        this.überzahlungsKonto = überzahlungsKonto;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        MonetaryAmount saldo = repository.saldo(abrechnung);
        Werte alteBuchung = repository.getAktuelleWerte(getArt(), abrechnung);
        MonetaryAmount alteÜberzahlung = alteBuchung.summe();
        MonetaryAmount reduziertesSaldo = saldo.subtract(alteÜberzahlung);
        Werte w = new Werte();
        if (reduziertesSaldo.isNegative()) {
            w.put(überzahlungsKonto, reduziertesSaldo);
        }
       
        return w;
    }

    @Override
    public int getArt() {
        return 0;
    }

    @Override
    public String getBuchungsText() {
        return "Überzahlungen";
    }

}
