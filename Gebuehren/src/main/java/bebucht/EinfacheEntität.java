package bebucht;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import test.BebuchtTestEntität;
import test.BebuchtTestEntität.Art;
import test.BebuchtTestEntität.EStatus;
import beans.Geld;

public abstract class EinfacheEntität extends BebuchungsEntitätImpl {

    protected Art art;
    protected long referenzId;
    protected MonetaryAmount betrag;
    protected HashMap<Enum<?>,MonetaryAmount> andereBeträge;
    
    public EinfacheEntität(Art art, long referenzId, MonetaryAmount betrag,BuchungsRepository repository,Enum<?> initialerStatus) {
        super(initialerStatus);
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        andereBeträge = new HashMap<>();
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
        andereBeträge.put(status, betrag);
    }


    public MonetaryAmount getBetrag(Enum<?> status) {
        MonetaryAmount betrag = andereBeträge.get(status);
        if (betrag == null) {
            betrag = Geld.getNull();
        }
        return betrag;
    }

}