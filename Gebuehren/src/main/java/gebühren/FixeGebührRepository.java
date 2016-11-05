package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Repository;

public interface FixeGebührRepository extends Repository{
    MonetaryAmount getFixeGebühr();
}
