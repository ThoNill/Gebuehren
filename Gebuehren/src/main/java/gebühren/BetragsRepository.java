package geb�hren;

import javax.money.MonetaryAmount;

public interface BetragsRepository extends MarkierendesRepository{
    MonetaryAmount getBetrag();
   
}
