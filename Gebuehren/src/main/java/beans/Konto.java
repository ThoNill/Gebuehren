package beans;

import javax.money.MonetaryAmount;

public interface Konto {
    int getNummer();
    String getName();
    boolean hasErgänzung();
    Werte ergänzen(MonetaryAmount amount);
}
