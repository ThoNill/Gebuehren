package beans;

import javax.money.MonetaryAmount;

public interface Konto {
    int getNummer();
    String getName();
    boolean hasErgänzung();
    Bewegungen ergänzen(MonetaryAmount amount);
}
