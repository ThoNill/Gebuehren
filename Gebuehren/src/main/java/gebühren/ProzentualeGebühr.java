package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Konto;

public class ProzentualeGebühr extends BetragsabhängigeGebühr<ProzentualRepository> {
    
    public ProzentualeGebühr(ProzentualRepository repository,
            Enum<?> art, String buchungsText, Konto betragsKonto,Konto gebührenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText,betragsKonto,gebührenKonto,abrechnung);
       
    }

 

    @Override
    protected MonetaryAmount getGebühr(ProzentualRepository repository, MonetaryAmount betrag) {
        double prozentsatz = repository.getGebührenProzentsatz();
        return Geld.percentAmount(betrag,prozentsatz);
   
    }

}
