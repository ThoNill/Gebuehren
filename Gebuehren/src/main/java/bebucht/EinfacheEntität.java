package bebucht;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import beans.BuchungsAuftrag;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public abstract class EinfacheEntit�t extends BebuchungsEntit�tImpl {

    protected Enum<?> art;
    protected long referenzId;
    protected MonetaryAmount betrag;
    protected Werte andereBetr�ge;

    public EinfacheEntit�t(Enum<?> art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository, Enum<?> initialerStatus) {
        super(initialerStatus);
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        andereBetr�ge = new Werte();
        this.status = initialerStatus;
    }

    @Override
    public long getId() {
        return referenzId;
    }

    @Override
    public Enum<?> getArt() {
        return art;
    }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }

    @Override
    public void addBetrag(Konto konto, MonetaryAmount betrag) {
        MonetaryAmount bisher = andereBetr�ge.get(konto);
        MonetaryAmount summe = bisher.add(betrag);
        andereBetr�ge.put(konto, summe);
    }

    public MonetaryAmount getBetrag(Konto konto) {
        MonetaryAmount betrag = andereBetr�ge.get(konto);
        if (betrag == null) {
            betrag = Geld.getNull();
        }
        return betrag;
    }

    protected void buchen(BuchungsRepository reporitory, Enum<?> art,
            String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = erzeugeBuchungsAuftrag(reporitory,
                art, buchungsText, betrag);
        reporitory.insertBuchung(auftrag);

    }

    protected void buchen(BuchungsRepository reporitory,
            EinfacheEntit�t gegenpart, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = erzeugeBuchungsAuftrag(reporitory,
                gegenpart, art, buchungsText, betrag);
        reporitory.insertBuchung(auftrag);
    }

    private BuchungsAuftragMitEntit�t erzeugeBuchungsAuftrag(
            BuchungsRepository reporitory, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = new BuchungsAuftragMitEntit�t(art,
                buchungsText);
        this.erg�nze�bergang(reporitory, auftrag, art, betrag);
        return auftrag;
    }

    private BuchungsAuftragMitEntit�t erzeugeBuchungsAuftrag(
            BuchungsRepository reporitory, EinfacheEntit�t gegenpart,
            Enum<?> art, String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = new BuchungsAuftragMitEntit�t(art,
                buchungsText);
        this.erg�nze�bergang(reporitory, auftrag, art, betrag);
        gegenpart.erg�nze�bergang(reporitory, auftrag, art, betrag.negate());
        return auftrag;
    }

    private void erg�nze�bergang(BuchungsRepository reporitory,
            BuchungsAuftragMitEntit�t auftrag, Enum<?> art,
            MonetaryAmount betrag) {
        �bergang �bergang = getM�gliche�berg�nge().get�bergang(art);
        erg�nze�bergang(reporitory, auftrag, �bergang, betrag);
    }

    private void erg�nze�bergang(BuchungsRepository reporitory,
            BuchungsAuftragMitEntit�t auftrag, �bergang �bergang,
            MonetaryAmount betrag) {

        if (!status.equals(�bergang.getVonStatus())) {
            throw new IllegalArgumentException("Die Entit�t " + this
                    + " ist im falschen Status");
        }

        Werte werte = auftrag.getWerte();
        if (!betrag.isZero()) {
            werte.put(�bergang.getNachKonto(), betrag);
            werte.put(reporitory.getGegenKonto(this), betrag.negate());
            addBetrag(�bergang.getVonKonto(), betrag.negate());
            addBetrag(�bergang.getNachKonto(), betrag);
        }
        setStatus(�bergang.getNachStatus());
        auftrag.addEntit�t(this);
    }

}