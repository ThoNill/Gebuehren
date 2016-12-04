package bebucht;

import javax.money.MonetaryAmount;

import buchung.Konto;
/**
 * 
 * @author Thomas Nill
 * 
 * Eine BebuchteEntit�t hat verschiedene Betragsangaben, eine Forderung etwa Soll,Haben, Rest,
 * ein Zahlungseingang Bezahlt, zugeordnet, offen.
 * 
 * Bei einem {@link �bergang} wechselt ein Geldbetrag seine Position innerhalb diese Betr�ge.
 * Bei jedem {@link �bergang} wird auch ein {@link Buchungsauftrag} erzeugt, der in ein  Repository
 * eingetragen werden kann.
 * 
 */
public interface BebuchteEntit�t {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        �bergangsGruppe getM�gliche�berg�nge();
        MonetaryAmount getBetrag();
        MonetaryAmount getBetrag(Konto konto);
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
