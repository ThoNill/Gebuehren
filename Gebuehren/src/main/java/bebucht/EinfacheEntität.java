package bebucht;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import test.BebuchtTestEntit�t;
import test.BebuchtTestEntit�t.Art;
import test.BebuchtTestEntit�t.EStatus;
import beans.Geld;

public abstract class EinfacheEntit�t extends BebuchungsEntit�tImpl {

    protected Art art;
    protected long referenzId;
    protected MonetaryAmount betrag;
    protected HashMap<Enum<?>,MonetaryAmount> andereBetr�ge;
    
    public EinfacheEntit�t(Art art, long referenzId, MonetaryAmount betrag,BuchungsRepository repository,Enum<?> initialerStatus) {
        super(initialerStatus);
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        andereBetr�ge = new HashMap<>();
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
    public void addBetrag(Enum<?> status, MonetaryAmount betrag) {
        MonetaryAmount bisher = getBetrag(status);
        MonetaryAmount summe = bisher.add(betrag);
        andereBetr�ge.put(status, betrag);
    }


    public MonetaryAmount getBetrag(Enum<?> status) {
        MonetaryAmount betrag = andereBetr�ge.get(status);
        if (betrag == null) {
            betrag = Geld.getNull();
        }
        return betrag;
    }

}