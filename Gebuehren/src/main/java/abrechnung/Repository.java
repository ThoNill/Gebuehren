package abrechnung;

import javax.money.MonetaryAmount;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

public interface Repository {
    Bewegungen getAktuelleWerte(Enum<?> art,Abrechnung a);
    MonetaryAmount saldo(Abrechnung a);
    public void insertBuchung(Abrechnung abrechnung,BuchungsAuftrag auftrag);
    double getMwstSatz(Abrechnung abrechnung);
    Konto getMwstKonto(Abrechnung abrechnung);

}
