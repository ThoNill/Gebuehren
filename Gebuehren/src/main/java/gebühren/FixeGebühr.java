package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.Konto;

public class FixeGebühr extends EinfacheGebühr<FixeGebührRepository> {
     
    public FixeGebühr(FixeGebührRepository repository, 
            Enum<?> art, String buchungsText,Konto gebührenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText,gebührenKonto,abrechnung);
    }

    @Override
    protected MonetaryAmount getGebühr(FixeGebührRepository repository) {
        return repository.getFixeGebühr();
    }

}
