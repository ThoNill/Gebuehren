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
    
    public �berzahlung(Repository repository,Konto �berzahlungsKonto) {
        this.repository = repository;
        this.�berzahlungsKonto = �berzahlungsKonto;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        MonetaryAmount saldo = repository.saldo(abrechnung);
        Werte alteBuchung = repository.getAktuelleWerte(getArt(), abrechnung);
        MonetaryAmount alte�berzahlung = alteBuchung.summe();
        MonetaryAmount reduziertesSaldo = saldo.subtract(alte�berzahlung);
        Werte w = new Werte();
        if (reduziertesSaldo.isNegative()) {
            w.put(�berzahlungsKonto, reduziertesSaldo);
        }
       
        return w;
    }

    @Override
    public int getArt() {
        return 0;
    }

    @Override
    public String getBuchungsText() {
        return "�berzahlungen";
    }

}
