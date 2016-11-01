package gebuehren;

import javax.money.MonetaryAmount;

public interface FixeGebührRepository extends BetragsRepository{
    MonetaryAmount getGebühr();
}
