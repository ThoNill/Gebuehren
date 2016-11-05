package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Werte;
import beans.impl.AutoMwstKonto;

public abstract class BetragsabhängigeGebühr<REPO extends BetragsRepository> extends EinfacheGebühr<REPO> {

    protected Konto betragsKonto;

    public BetragsabhängigeGebühr(REPO repository, Enum<?> art, String buchungsText,Konto betragsKonto,Konto gebührenKonto) {
        super(repository, art, buchungsText,gebührenKonto);
        this.betragsKonto = betragsKonto;
    }

    protected abstract MonetaryAmount getGebühr(REPO repository, Abrechnung abrechnung,MonetaryAmount betrag);
    
    
    protected MonetaryAmount getGebühr(REPO repository,Abrechnung abrechnung)  {
        MonetaryAmount betrag = repository.getBetrag();
        return getGebühr(repository, abrechnung,betrag);
        
    }
    
    @Override
    protected Werte getWerte(REPO repository, Abrechnung abrechnung) {
        Werte w = super.getWerte(repository,abrechnung);
        MonetaryAmount betrag = repository.getBetrag();
        w.put(betragsKonto, betrag);
        return w;
    }
}