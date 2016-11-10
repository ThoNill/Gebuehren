package bebucht;

import javax.money.MonetaryAmount;

import buchung.Konto;

public interface BebuchteEntität {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        ÜbergangsGruppe getMöglicheÜbergänge();
        MonetaryAmount getBetrag();
        void addBetrag(Konto konto, MonetaryAmount betrag);
}
