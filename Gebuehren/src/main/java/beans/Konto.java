package beans;

import javax.money.MonetaryAmount;

public interface Konto {
    int getNummer();
    String getName();
    boolean hasErg�nzung();
    Bewegungen erg�nzen(MonetaryAmount amount);
}
