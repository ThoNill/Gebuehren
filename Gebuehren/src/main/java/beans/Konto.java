package beans;

import javax.money.MonetaryAmount;

public interface Konto {
    int getNummer();
    String getName();
    boolean hasErg�nzung();
    Werte erg�nzen(MonetaryAmount amount);
}
