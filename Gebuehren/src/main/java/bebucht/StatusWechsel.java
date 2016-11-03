package bebucht;

import javax.money.MonetaryAmount;

import beans.BuchungsartUndText;
import beans.Werte;

public class StatusWechsel extends BuchungsartUndText {
    private Enum status;
 
    public StatusWechsel(Enum art, String buchungsText,Enum status) {
        super(art, buchungsText);
        this.status = status;
    }

    public StatusWechsel(Enum art,Enum status) {
        super(art, null);
    }

    public boolean mitBuchung() {
        return getBuchungsText() != null;
    }
    
    public BuchungsAuftragMitEntit�t erzeugeBuchungsAuftragMitPr�fung(
            BuchungsRepository reporitory, BebuchteEntit�t entit�t,
            MonetaryAmount betrag) {
        if (status == entit�t.getStatus()) {
            return erzeugeBuchungsAuftrag(reporitory, entit�t, betrag);
        } else {
            throw new IllegalArgumentException("Die Entit�t " + entit�t
                    + " ist im falschen Status");
        }
    }  

    protected BuchungsAuftragMitEntit�t erzeugeBuchungsAuftrag(
            BuchungsRepository reporitory, BebuchteEntit�t entit�t,
            MonetaryAmount betrag) {
        Werte werte = new Werte();
        if (!betrag.isZero() && mitBuchung()) {
            werte.put(reporitory.getSachKonto(entit�t), betrag);
            werte.put(reporitory.getGegenKonto(entit�t), betrag);
        }
        return new BuchungsAuftragMitEntit�t(getArt(), getBuchungsText(),
                werte, entit�t);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + status.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatusWechsel other = (StatusWechsel) obj;
        if (status != other.status)
            return false;
        return true;
    }

    public Enum getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StatusWechsel [status=" + status + "]";
    }

}
