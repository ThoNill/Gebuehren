package gebühren;

import javax.money.MonetaryAmount;

public interface FixeGebührRepository extends MarkierendesRepository{
    MonetaryAmount getFixeGebühr();
}
