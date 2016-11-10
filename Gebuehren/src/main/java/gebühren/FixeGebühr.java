package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Konto;

public class FixeGebühr extends EinfacheGebühr<FixeGebührRepository> {
    protected Konto gebührenKonto;
    
    public FixeGebühr(FixeGebührRepository repository, 
            Enum<?> art, String buchungsText,Konto gebührenKonto) {
        super(repository, art, buchungsText,gebührenKonto);
    }

    @Override
    protected MonetaryAmount getGebühr(FixeGebührRepository repository,
            Abrechnung abrechnung) {
        return repository.getFixeGebühr();
    }

}
