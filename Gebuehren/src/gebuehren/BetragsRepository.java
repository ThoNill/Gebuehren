package gebuehren;

import javax.money.MonetaryAmount;

import abrechnung.Repository;

public interface BetragsRepository extends Repository{
    MonetaryAmount getBetrag();
   
}
