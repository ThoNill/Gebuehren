package gebuehren;

import javax.money.MonetaryAmount;

public interface FixeGeb�hrRepository extends BetragsRepository{
    MonetaryAmount getGeb�hr();
}
