package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class Betragsabh�ngigeGeb�hr<REPO extends BetragsRepository> extends EinfacheGeb�hr<REPO> {

    protected Konto betragsKonto;

    public Betragsabh�ngigeGeb�hr(REPO repository, Enum<?> art, String buchungsText,Konto betragsKonto,Konto geb�hrenKonto) {
        super(repository, art, buchungsText,geb�hrenKonto);
        this.betragsKonto = betragsKonto;
    }

    protected abstract MonetaryAmount getGeb�hr(REPO repository, Abrechnung abrechnung,MonetaryAmount betrag);
    
    
    @Override
    protected MonetaryAmount getGeb�hr(REPO repository,Abrechnung abrechnung)  {
        MonetaryAmount betrag = repository.getBetrag();
        return getGeb�hr(repository, abrechnung,betrag);
        
    }
    
    @Override
    protected Bewegungen getWerte(REPO repository, Abrechnung abrechnung) {
        Bewegungen w = super.getWerte(repository,abrechnung);
        MonetaryAmount betrag = repository.getBetrag();
        w.put(betragsKonto, betrag);
        return w;
    }
}