package bebucht;

import javax.money.MonetaryAmount;

import beans.BuchungsAuftrag;
import beans.Werte;

public interface BebuchteEntität {
        long getReferenz();
        Enum getReferenzArt();
        Enum  getStatus();
        StatusWechselGruppe getMöglicheStatusWechsel();
        MonetaryAmount getBetrag();
        default void setStatus(Enum nachStatus,BuchungsRepository repository) {
            setStatus(nachStatus, repository,getBetrag());
        }
        void setStatus(Enum nachStatus, BuchungsRepository repository,MonetaryAmount betrag);
         
}
