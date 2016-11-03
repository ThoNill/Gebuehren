package bebucht;

import javax.money.MonetaryAmount;

public abstract class BebuchungsEntitätImpl implements BebuchteEntität{
    protected  Enum<?> status;


    @Override
    public void setStatus(Enum<?> nachStatus, BuchungsRepository repository,MonetaryAmount betrag) {
        StatusWechselGruppe möglicheStatusWechsel = getMöglicheStatusWechsel();
        buchen(repository, betrag.negate(), möglicheStatusWechsel);
        status = nachStatus;
        buchen(repository, betrag, möglicheStatusWechsel);
    }


    protected void buchen(BuchungsRepository repository, MonetaryAmount betrag,
            StatusWechselGruppe möglicheStatusWechsel) {
        StatusWechsel von = möglicheStatusWechsel.getWechsel(status);
        if (von != null) {
            repository.insertBuchung(von.erzeugeBuchungsAuftrag(repository, this, betrag));
        }
    }


    @Override
    public Enum<?> getStatus() {
        return status;
    }
  

}
