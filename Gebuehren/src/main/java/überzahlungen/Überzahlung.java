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
    private Enum art;
    
    public �berzahlung(Enum art,Repository repository,Konto �berzahlungsKonto) {
        this.repository = repository;
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.art = art;
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
    public Enum getArt() {
        return art;
    }

    @Override
    public String getBuchungsText() {
        return "�berzahlungen";
    }

}
