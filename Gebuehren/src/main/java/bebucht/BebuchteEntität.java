package bebucht;

import javax.money.MonetaryAmount;

public interface BebuchteEntität {
        long getId();
        Enum<?> getArt();
        Enum<?>  getStatus();
        StatusWechselGruppe getMöglicheStatusWechsel();
        MonetaryAmount getBetrag();
        default void setStatus(Enum<?> nachStatus,BuchungsRepository repository) {
            setStatus(nachStatus, repository,getBetrag());
        }
        void setStatus(Enum<?> nachStatus, BuchungsRepository repository,MonetaryAmount betrag);
        void addBetrag(Enum<?> status, MonetaryAmount betrag);
         
}
