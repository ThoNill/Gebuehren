package test;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import org.junit.internal.runners.model.EachTestNotifier;

import bebucht.BebuchteEntit�t;
import bebucht.BuchungsRepository;
import bebucht.EinfacheEntit�t;
import bebucht.StatusWechselGruppe;

public class BebuchtTestEntit�t extends EinfacheEntit�t {
    public enum Art {
        AUFTRAG, LIEFERSCHEIN, RECHNUNG, GUTSCHRIFT
    }

    public enum EStatus {
        INIT, ANGELEGT, GEBUCHT, STORNIERT
    }

    public BebuchtTestEntit�t(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, EStatus.INIT);
        setStatus(EStatus.ANGELEGT, repository);
    }

    @Override
    public StatusWechselGruppe getM�glicheStatusWechsel() {
        return new StatusWechselGruppe().addStatusWechsel(art,
                art.name() + "angelegt", EStatus.ANGELEGT).addStatusWechsel(
                art, art.name() + " storniert", EStatus.STORNIERT);

    }

}
