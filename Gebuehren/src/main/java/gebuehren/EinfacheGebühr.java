package gebuehren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public abstract class EinfacheGeb�hr<REPO extends BetragsRepository> extends Geb�hr<REPO> {

    protected Konto geb�hrenKonto;
    protected Konto betragsKonto;

    public EinfacheGeb�hr(REPO repository, int art, String buchungsText,Konto betragsKonto,Konto geb�hrenKonto) {
        super(repository, art, buchungsText);
        this.betragsKonto = betragsKonto;
        this.geb�hrenKonto = geb�hrenKonto;
    }

    protected abstract MonetaryAmount getGeb�hr(REPO repository, Abrechnung abrechnung,MonetaryAmount betrag);
        
    
    @Override
    protected Werte getWerte(REPO repository, Abrechnung abrechnung) {
        MonetaryAmount betrag = repository.getBetrag();
        MonetaryAmount geb�hr = getGeb�hr(repository, abrechnung,betrag);
        double mwstSatz = repository.getMwstSatz(abrechnung);
        MonetaryAmount mwst = Geld.percentAmount(geb�hr,mwstSatz);

        Werte w = new Werte();
        w.put(betragsKonto, betrag);
        w.put(geb�hrenKonto, geb�hr.negate());
        w.put(repository.getMwstKonto(abrechnung), mwst.negate());
        return w;
    }
}