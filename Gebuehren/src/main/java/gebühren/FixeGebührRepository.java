package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Repository;

public interface FixeGeb�hrRepository extends Repository{
    MonetaryAmount getFixeGeb�hr();
}
