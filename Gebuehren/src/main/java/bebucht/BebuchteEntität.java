package bebucht;

import javax.money.MonetaryAmount;

import buchung.Konto;

public interface BebuchteEntit�t {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        �bergangsGruppe getM�gliche�berg�nge();
        MonetaryAmount getBetrag();
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
