package bebucht;

import javax.money.MonetaryAmount;

import beans.Konto;

public interface BebuchteEntität {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        ÜbergangsGruppe getMöglicheÜbergänge();
        MonetaryAmount getBetrag();
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
