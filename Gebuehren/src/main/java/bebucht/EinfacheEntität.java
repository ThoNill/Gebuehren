package bebucht;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import beans.BuchungsAuftrag;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public abstract class EinfacheEntität extends BebuchungsEntitätImpl {

    protected Enum<?> art;
    protected long referenzId;
    protected MonetaryAmount betrag;
    protected Werte andereBeträge;

    public EinfacheEntität(Enum<?> art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository, Enum<?> initialerStatus) {
        super(initialerStatus);
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        andereBeträge = new Werte();
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
        MonetaryAmount bisher = andereBeträge.get(konto);
        MonetaryAmount summe = bisher.add(betrag);
        andereBeträge.put(konto, summe);
    }

    public MonetaryAmount getBetrag(Konto konto) {
        MonetaryAmount betrag = andereBeträge.get(konto);
        if (betrag == null) {
            betrag = Geld.getNull();
        }
        return betrag;
    }

    protected void buchen(BuchungsRepository reporitory, Enum<?> art,
            String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = erzeugeBuchungsAuftrag(reporitory,
                art, buchungsText, betrag);
        reporitory.insertBuchung(auftrag);

    }

    protected void buchen(BuchungsRepository reporitory,
            EinfacheEntität gegenpart, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = erzeugeBuchungsAuftrag(reporitory,
                gegenpart, art, buchungsText, betrag);
        reporitory.insertBuchung(auftrag);
    }

    private BuchungsAuftragMitEntität erzeugeBuchungsAuftrag(
            BuchungsRepository reporitory, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = new BuchungsAuftragMitEntität(art,
                buchungsText);
        this.ergänzeÜbergang(reporitory, auftrag, art, betrag);
        return auftrag;
    }

    private BuchungsAuftragMitEntität erzeugeBuchungsAuftrag(
            BuchungsRepository reporitory, EinfacheEntität gegenpart,
            Enum<?> art, String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = new BuchungsAuftragMitEntität(art,
                buchungsText);
        this.ergänzeÜbergang(reporitory, auftrag, art, betrag);
        gegenpart.ergänzeÜbergang(reporitory, auftrag, art, betrag.negate());
        return auftrag;
    }

    private void ergänzeÜbergang(BuchungsRepository reporitory,
            BuchungsAuftragMitEntität auftrag, Enum<?> art,
            MonetaryAmount betrag) {
        Übergang übergang = getMöglicheÜbergänge().getÜbergang(art);
        ergänzeÜbergang(reporitory, auftrag, übergang, betrag);
    }

    private void ergänzeÜbergang(BuchungsRepository reporitory,
            BuchungsAuftragMitEntität auftrag, Übergang übergang,
            MonetaryAmount betrag) {

        if (!status.equals(übergang.getVonStatus())) {
            throw new IllegalArgumentException("Die Entität " + this
                    + " ist im falschen Status");
        }

        Werte werte = auftrag.getWerte();
        if (!betrag.isZero()) {
            werte.put(übergang.getNachKonto(), betrag);
            werte.put(reporitory.getGegenKonto(this), betrag.negate());
            addBetrag(übergang.getVonKonto(), betrag.negate());
            addBetrag(übergang.getNachKonto(), betrag);
        }
        setStatus(übergang.getNachStatus());
        auftrag.addEntität(this);
    }

}