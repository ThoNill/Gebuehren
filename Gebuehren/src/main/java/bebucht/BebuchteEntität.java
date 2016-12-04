package bebucht;

import javax.money.MonetaryAmount;

import buchung.Konto;
/**
 * 
 * @author Thomas Nill
 * 
 * Eine BebuchteEntität hat verschiedene Betragsangaben, eine Forderung etwa Soll,Haben, Rest,
 * ein Zahlungseingang Bezahlt, zugeordnet, offen.
 * 
 * Bei einem {@link Übergang} wechselt ein Geldbetrag seine Position innerhalb diese Beträge.
 * Bei jedem {@link Übergang} wird auch ein {@link Buchungsauftrag} erzeugt, der in ein  Repository
 * eingetragen werden kann.
 * 
 */
public interface BebuchteEntität {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        ÜbergangsGruppe getMöglicheÜbergänge();
        MonetaryAmount getBetrag();
        MonetaryAmount getBetrag(Konto konto);
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
