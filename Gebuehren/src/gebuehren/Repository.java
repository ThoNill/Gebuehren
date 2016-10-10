package gebuehren;

import javax.money.MonetaryAmount;

import beans.Abrechnung;

public interface Repository {
    Werte getAktuelleWerte(Gebuehr g,Abrechnung a);
    MonetaryAmount saldo(Abrechnung a);
    public void insertBuchung(Abrechnung a, Gebuehr g, Werte werte);

}
