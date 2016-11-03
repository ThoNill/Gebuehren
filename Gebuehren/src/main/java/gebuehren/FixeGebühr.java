package gebuehren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Konto;

public class FixeGeb�hr extends EinfacheGeb�hr<FixeGeb�hrRepository> {
    public FixeGeb�hr(FixeGeb�hrRepository repository, 
            Enum art, String buchungsText, Konto betragsKonto,Konto geb�hrenKonto) {
        super(repository, art, buchungsText,betragsKonto,geb�hrenKonto);
     
    }

 
    @Override
    protected MonetaryAmount getGeb�hr(FixeGeb�hrRepository repository,
            Abrechnung abrechnung,MonetaryAmount betrag) {
        return repository.getGeb�hr();
   
    }

}
