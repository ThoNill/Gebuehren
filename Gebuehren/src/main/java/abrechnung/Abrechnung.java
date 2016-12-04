package abrechnung;

import javax.money.MonetaryAmount;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine Abrechnung ist eine Liste von BewegungenQuelle, die beim Abrechnen eine Reihe von Buchungen erzeugen.
 * Jede BewegungenQuelle erzeugt eine Buchung. Diese wird mit eventuell schon vorhandenen Buchungen desselben Typs
 * verglichen. Dann wird die Differenz zwischen Soll und Istzustand gebucht.
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
