package test;

import java.util.HashMap;

import javax.money.MonetaryAmount;

import org.junit.internal.runners.model.EachTestNotifier;

import bebucht.BebuchteEntität;
import bebucht.BuchungsRepository;
import bebucht.EinfacheEntität;
import bebucht.StatusWechselGruppe;

public class BebuchtTestEntität extends EinfacheEntität {
    public enum Art {
        AUFTRAG, LIEFERSCHEIN, RECHNUNG, GUTSCHRIFT
    }

    public enum EStatus {
        INIT, ANGELEGT, GEBUCHT, STORNIERT
    }

    public BebuchtTestEntität(Art art, long referenzId, MonetaryAmount betrag,
            BuchungsRepository repository) {
        super(art, referenzId, betrag, repository, EStatus.INIT);
        setStatus(EStatus.ANGELEGT, repository);
    }

    @Override
    public StatusWechselGruppe getMöglicheStatusWechsel() {
        return new StatusWechselGruppe().addStatusWechsel(art,
                art.name() + "angelegt", EStatus.ANGELEGT).addStatusWechsel(
                art, art.name() + " storniert", EStatus.STORNIERT);

    }

}
