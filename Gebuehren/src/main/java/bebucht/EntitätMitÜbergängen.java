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
 * Eine {@link Entit�tMitStatus} die eine Reihe von Betr�gen verwaltet.
 * bei einem {@link �bergang} wir Geld zwischen diesen Betr�gen verschoben
 * und {@link BuchungsAuftrag} erzeugt.
 * 
 *
 */
public abstract class Entit�tMit�berg�ngen extends Entit�tMitStatus {
 
    private MonetaryAmount betrag;
    private Bewegungen andereBetr�ge;

    public Entit�tMit�berg�ngen(Enum<?> art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository, Enum<?> initialerStatus) {
        super(art,referenzId, initialerStatus);
        this.betrag = betrag;
        andereBetr�ge = new Bewegungen();
     }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }
    
    @Override
    public void addBetrag(Konto konto, MonetaryAmount betrag) {
        andereBetr�ge.add(konto,betrag);
    }
    
    @Override
    public MonetaryAmount getBetrag(Konto konto) {
        MonetaryAmount betrag = andereBetr�ge.get(konto);
        if (betrag == null) {
            betrag = Geld.getNull();
        }
        return betrag;
    }


    protected void buchen(BuchungsRepository repository, Enum<?> art,
            String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = erzeugeBuchungsAuftrag(repository,
                art, buchungsText, betrag);
        repository.insertBuchung(auftrag);

    }

    protected void buchen(BuchungsRepository repository,
            Entit�tMit�berg�ngen gegenpart, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = erzeugeBuchungsAuftrag(repository,
                gegenpart, art, buchungsText, betrag);
        repository.insertBuchung(auftrag);
    }

    private BuchungsAuftragMitEntit�t erzeugeBuchungsAuftrag(
            BuchungsRepository repository, Enum<?> art, String buchungsText,
            MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = new BuchungsAuftragMitEntit�t(art,
                buchungsText);
        this.erg�nze�bergang(repository, auftrag, art, betrag);
        return auftrag;
    }

    private BuchungsAuftragMitEntit�t erzeugeBuchungsAuftrag(
            BuchungsRepository repository, Entit�tMit�berg�ngen gegenpart,
            Enum<?> art, String buchungsText, MonetaryAmount betrag) {
        BuchungsAuftragMitEntit�t auftrag = new BuchungsAuftragMitEntit�t(art,
                buchungsText);
        this.erg�nze�bergang(repository, auftrag, art, betrag);
        gegenpart.erg�nze�bergang(repository, auftrag, art, betrag.negate());
        return auftrag;
    }

    private void erg�nze�bergang(BuchungsRepository repository,
            BuchungsAuftragMitEntit�t auftrag, Enum<?> art,
            MonetaryAmount betrag) {
        �bergang �bergang = getM�gliche�berg�nge().get�bergang(art);
        erg�nze�bergang(repository, auftrag, �bergang, betrag);
    }

    private void erg�nze�bergang(BuchungsRepository repository,
            BuchungsAuftragMitEntit�t auftrag, �bergang �bergang,
            MonetaryAmount betrag) {

        if (!getStatus().equals(�bergang.getVonStatus())) {
            throw new IllegalArgumentException("Die Entit�t " + this
                    + " ist im falschen Status");
        }

        Bewegungen bewegungen = auftrag.getWerte();
        if (!betrag.isZero()) {
            bewegungen.add(�bergang.getNachKonto(), betrag);
            bewegungen.add(repository.getGegenKonto(this), betrag.negate());
            addBetrag(�bergang.getVonKonto(), betrag.negate());
            addBetrag(�bergang.getNachKonto(), betrag);
        }
        setStatus(�bergang.getNachStatus());
        auftrag.addEntit�t(this);
    }

}