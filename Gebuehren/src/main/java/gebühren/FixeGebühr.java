package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Konto;

public class FixeGeb�hr extends EinfacheGeb�hr<FixeGeb�hrRepository> {
     
    public FixeGeb�hr(FixeGeb�hrRepository repository, 
            Enum<?> art, String buchungsText,Konto geb�hrenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText,geb�hrenKonto,abrechnung);
    }

    @Override
    protected MonetaryAmount getGeb�hr(FixeGeb�hrRepository repository) {
        return repository.getFixeGeb�hr();
    }

}
