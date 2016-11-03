package abrechnung;

import javax.money.MonetaryAmount;

import beans.BuchungsartUndText;
import beans.Konto;
import beans.Werte;

public interface Repository {
    Werte getAktuelleWerte(Enum<?> art,Abrechnung a);
    MonetaryAmount saldo(Abrechnung a);
    public void insertBuchung(Abrechnung abrechnung,BuchungsartUndText auftrag);
    double getMwstSatz(Abrechnung abrechnung);
    Konto getMwstKonto(Abrechnung abrechnung);

}
