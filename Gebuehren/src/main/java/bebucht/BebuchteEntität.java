package bebucht;

import javax.money.MonetaryAmount;

import beans.Konto;

public interface BebuchteEntit�t {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        �bergangsGruppe getM�gliche�berg�nge();
        MonetaryAmount getBetrag();
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
