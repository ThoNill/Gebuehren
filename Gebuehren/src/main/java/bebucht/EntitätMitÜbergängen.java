package bebucht;

import javax.money.MonetaryAmount;

import betrag.Geld;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;


/**
 * 
 * @author Thomas Nill
 * 
 * Eine {@link EntitätMitStatus} die eine Reihe von Beträgen verwaltet.
 * bei einem {@link Übergang} wir Geld zwischen diesen Beträgen verschoben
 * und {@link BuchungsAuftrag} erzeugt.
 * 
 *
 */
public abstract class EntitätMitÜbergängen extends EntitätMitStatus {
 
    private MonetaryAmount betrag;
    private Bewegungen andereBeträge;

    public EntitätMitÜbergängen(Enum<?> art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository, Enum<?> initialerStatus) {
        super(art,referenzId, initialerStatus);
        this.betrag = betrag;
        andereBeträge = new Bewegungen();
     }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }
    
    @Override
    public void addBetrag(Konto konto, MonetaryAmount betrag) {
        andereBeträge.add(konto,betrag);
    }
    
    @Override
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

        if (!getStatus().equals(übergang.getVonStatus())) {
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