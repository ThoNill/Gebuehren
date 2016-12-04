package abrechnung;

import javax.money.MonetaryAmount;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine Abrechnung ist ein zeitlich abgegrenzter Bereich. Übernimmt die Aufgabe eines Repository
 *
 */
public interface Abrechnung {
     MonetaryAmount getSaldo();
     double getMwstSatz();
     Konto getMwstKonto();
     Bewegungen getAktuelleWerte(Enum<?> art);
     Abrechnung nächsteAbrechnung();
     void insertBuchung(BuchungsAuftrag auftrag);
}
