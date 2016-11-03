package test;

import javax.money.MonetaryAmount;

import org.junit.internal.runners.model.EachTestNotifier;

import bebucht.BebuchteEntität;
import bebucht.BebuchungsEntitätImpl;
import bebucht.BuchungsRepository;
import bebucht.StatusWechselGruppe;

public class BebuchtTestEntität extends BebuchungsEntitätImpl {
    public enum Art {
        AUFTRAG, LIEFERSCHEIN, RECHNUNG, GUTSCHRIFT
    }

    public enum EStatus {
        INIT,ANGELEGT, GEBUCHT, STORNIERT
    }

    private Art art;
    long referenzId;
    MonetaryAmount betrag;

    public BebuchtTestEntität(Art art, long referenzId, MonetaryAmount betrag,BuchungsRepository repository) {
        super();
        this.status = EStatus.INIT;
        this.art = art;
        this.referenzId = referenzId;
        this.betrag = betrag;
        setStatus(EStatus.ANGELEGT, repository);
    }

    @Override
    public long getReferenz() {
        return referenzId;
    }

    @Override
    public Enum getReferenzArt() {
        return art;
    }

    @Override
    public StatusWechselGruppe getMöglicheStatusWechsel() {
        return new StatusWechselGruppe().addStatusWechsel(art,
                art.name() + "angelegt", EStatus.ANGELEGT).addStatusWechsel(
                art, art.name() + " storniert", EStatus.STORNIERT);

    }

    @Override
    public MonetaryAmount getBetrag() {
       return betrag;
    }

}
