package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Konto;

public class FixeGeb�hr extends EinfacheGeb�hr<FixeGeb�hrRepository> {
    protected Konto geb�hrenKonto;
    
    public FixeGeb�hr(FixeGeb�hrRepository repository, 
            Enum<?> art, String buchungsText,Konto geb�hrenKonto) {
        super(repository, art, buchungsText,geb�hrenKonto);
    }

    @Override
    protected MonetaryAmount getGeb�hr(FixeGeb�hrRepository repository,
            Abrechnung abrechnung) {
        return repository.getFixeGeb�hr();
    }

}
