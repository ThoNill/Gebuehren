package bebucht;

import javax.money.MonetaryAmount;

public abstract class BebuchungsEntit�tImpl implements BebuchteEntit�t{
    protected  Enum<?> status;


    @Override
    public void setStatus(Enum<?> nachStatus, BuchungsRepository repository,MonetaryAmount betrag) {
        StatusWechselGruppe m�glicheStatusWechsel = getM�glicheStatusWechsel();
        buchen(repository, betrag.negate(), m�glicheStatusWechsel);
        status = nachStatus;
        buchen(repository, betrag, m�glicheStatusWechsel);
    }


    protected void buchen(BuchungsRepository repository, MonetaryAmount betrag,
            StatusWechselGruppe m�glicheStatusWechsel) {
        StatusWechsel von = m�glicheStatusWechsel.getWechsel(status);
        if (von != null) {
            repository.insertBuchung(von.erzeugeBuchungsAuftrag(repository, this, betrag));
        }
    }


    @Override
    public Enum<?> getStatus() {
        return status;
    }
  

}
