package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class BetragsabhängigeGebühr<REPO extends BetragsRepository> extends EinfacheGebühr<REPO> {

    protected Konto betragsKonto;

    public BetragsabhängigeGebühr(REPO repository, Enum<?> art, String buchungsText,Konto betragsKonto,Konto gebührenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText,gebührenKonto,abrechnung);
        this.betragsKonto = betragsKonto;
    }

    protected abstract MonetaryAmount getGebühr(REPO repository, MonetaryAmount betrag);
    
    
    @Override
    protected MonetaryAmount getGebühr(REPO repository)  {
        MonetaryAmount betrag = repository.getBetrag();
        return getGebühr(repository,betrag);
        
    }
    
    @Override
    protected Bewegungen getWerte(REPO repository) {
        Bewegungen w = super.getWerte(repository);
        MonetaryAmount betrag = repository.getBetrag();
        w.put(betragsKonto, betrag);
        return w;
    }
}