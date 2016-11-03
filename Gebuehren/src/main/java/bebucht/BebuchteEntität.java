package bebucht;

import javax.money.MonetaryAmount;

import beans.BuchungsAuftrag;
import beans.Werte;

public interface BebuchteEntit�t {
        long getReferenz();
        Enum getReferenzArt();
        Enum  getStatus();
        StatusWechselGruppe getM�glicheStatusWechsel();
        MonetaryAmount getBetrag();
        default void setStatus(Enum nachStatus,BuchungsRepository repository) {
            setStatus(nachStatus, repository,getBetrag());
        }
        void setStatus(Enum nachStatus, BuchungsRepository repository,MonetaryAmount betrag);
         
}
