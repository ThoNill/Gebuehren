package gebuehren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Konto;

public class FixeGebühr extends EinfacheGebühr<FixeGebührRepository> {
    public FixeGebühr(FixeGebührRepository repository, 
            Enum art, String buchungsText, Konto betragsKonto,Konto gebührenKonto) {
        super(repository, art, buchungsText,betragsKonto,gebührenKonto);
     
    }

 
    @Override
    protected MonetaryAmount getGebühr(FixeGebührRepository repository,
            Abrechnung abrechnung,MonetaryAmount betrag) {
        return repository.getGebühr();
   
    }

}
