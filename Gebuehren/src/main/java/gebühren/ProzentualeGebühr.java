package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Konto;

public class ProzentualeGeb�hr extends Betragsabh�ngigeGeb�hr<ProzentualRepository> {
    
    public ProzentualeGeb�hr(ProzentualRepository repository,
            Enum<?> art, String buchungsText, Konto betragsKonto,Konto geb�hrenKonto) {
        super(repository, art, buchungsText,betragsKonto,geb�hrenKonto);
       
    }

 

    @Override
    protected MonetaryAmount getGeb�hr(ProzentualRepository repository,
            Abrechnung abrechnung,MonetaryAmount betrag) {
        double prozentsatz = repository.getGeb�hrenProzentsatz();
        return Geld.percentAmount(betrag,prozentsatz);
   
    }

}
