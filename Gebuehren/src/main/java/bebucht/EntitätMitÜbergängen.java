package bebucht;

import javax.money.MonetaryAmount;

import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class EntitätMitÜbergängen extends EntitätMitStatus {

    protected Enum<?> art;
    protected long referenzId;
    protected MonetaryAmount betrag;
    protected Bewegungen andereBeträge;

    public EntitätMitÜbergängen(Enum<?> art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository, Enum<?> initialerStatus) {
        super(initialerStatus);
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        andereBeträge = new Bewegungen();
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

    protected void buchen(BuchungsRepository repository, Enum<?> art,
            String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = erzeugeBuchungsAuftrag(repository,
                art, buchungsText, betrag);
        repository.insertBuchung(auftrag);

    }

    protected void buchen(BuchungsRepository repository,
            EntitätMitÜbergängen gegenpart, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = erzeugeBuchungsAuftrag(repository,
                gegenpart, art, buchungsText, betrag);
        repository.insertBuchung(auftrag);
    }

    private BuchungsAuftragMitEntität erzeugeBuchungsAuftrag(
            BuchungsRepository repository, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = new BuchungsAuftragMitEntität(art,
                buchungsText);
        this.ergänzeÜbergang(repository, auftrag, art, betrag);
        return auftrag;
    }

    private BuchungsAuftragMitEntität erzeugeBuchungsAuftrag(
            BuchungsRepository repository, EntitätMitÜbergängen gegenpart,
            Enum<?> art, String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntität auftrag = new BuchungsAuftragMitEntität(art,
                buchungsText);
        this.ergänzeÜbergang(repository, auftrag, art, betrag);
        gegenpart.ergänzeÜbergang(repository, auftrag, art, betrag.negate());
        return auftrag;
    }

    private void ergänzeÜbergang(BuchungsRepository repository,
            BuchungsAuftragMitEntität auftrag, Enum<?> art,
            MonetaryAmount betrag) {
        Übergang übergang = getMöglicheÜbergänge().getÜbergang(art);
        ergänzeÜbergang(repository, auftrag, übergang, betrag);
    }

    private void ergänzeÜbergang(BuchungsRepository repository,
            BuchungsAuftragMitEntität auftrag, Übergang übergang,
            MonetaryAmount betrag) {

        if (!status.equals(übergang.getVonStatus())) {
            throw new IllegalArgumentException("Die Entität " + this
                    + " ist im falschen Status");
        }

        Bewegungen bewegungen = auftrag.getWerte();
        if (!betrag.isZero()) {
            bewegungen.add(übergang.getNachKonto(), betrag);
            bewegungen.add(repository.getGegenKonto(this), betrag.negate());
            addBetrag(übergang.getVonKonto(), betrag.negate());
            addBetrag(übergang.getNachKonto(), betrag);
        }
        setStatus(übergang.getNachStatus());
        auftrag.addEntität(this);
    }

}