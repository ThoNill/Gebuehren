package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;

public class ProzentualeGebühr extends BetragsabhängigeGebühr<ProzentualRepository> {
    
    public ProzentualeGebühr(ProzentualRepository repository,
            Enum<?> art, String buchungsText, Konto betragsKonto,Konto gebührenKonto) {
        super(repository, art, buchungsText,betragsKonto,gebührenKonto);
       
    }

 

    @Override
    protected MonetaryAmount getGebühr(ProzentualRepository repository,
            Abrechnung abrechnung,MonetaryAmount betrag) {
        double prozentsatz = repository.getGebührenProzentsatz();
        return Geld.percentAmount(betrag,prozentsatz);
   
    }

}
