package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class BetragsabhängigeGebühr<REPO extends BetragsRepository> extends EinfacheGebühr<REPO> {

    protected Konto betragsKonto;

    public BetragsabhängigeGebühr(REPO repository, Enum<?> art, String buchungsText,Konto betragsKonto,Konto gebührenKonto) {
        super(repository, art, buchungsText,gebührenKonto);
        this.betragsKonto = betragsKonto;
    }

    protected abstract MonetaryAmount getGebühr(REPO repository, Abrechnung abrechnung,MonetaryAmount betrag);
    
    
    @Override
    protected MonetaryAmount getGebühr(REPO repository,Abrechnung abrechnung)  {
        MonetaryAmount betrag = repository.getBetrag();
        return getGebühr(repository, abrechnung,betrag);
        
    }
    
    @Override
    protected Bewegungen getWerte(REPO repository, Abrechnung abrechnung) {
        Bewegungen w = super.getWerte(repository,abrechnung);
        MonetaryAmount betrag = repository.getBetrag();
        w.put(betragsKonto, betrag);
        return w;
    }
}