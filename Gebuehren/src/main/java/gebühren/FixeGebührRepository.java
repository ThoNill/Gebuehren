package geb�hren;

import javax.money.MonetaryAmount;

public interface FixeGeb�hrRepository extends MarkierendesRepository{
    MonetaryAmount getFixeGeb�hr();
}
