package abrechnung;

import javax.money.MonetaryAmount;
import beans.BuchungsAuftrag;
import beans.Konto;
import beans.Werte;

public interface Repository {
    Werte getAktuelleWerte(int art,Abrechnung a);
    MonetaryAmount saldo(Abrechnung a);
    public void insertBuchung(Abrechnung abrechnung,BuchungsAuftrag auftrag);
    double getMwstSatz(Abrechnung abrechnung);
    Konto getMwstKonto(Abrechnung abrechnung);

}
