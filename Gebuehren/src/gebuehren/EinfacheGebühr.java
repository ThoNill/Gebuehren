package gebuehren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public abstract class EinfacheGebühr<REPO extends BetragsRepository> extends Gebühr<REPO> {

    protected Konto gebührenKonto;
    protected Konto betragsKonto;

    public EinfacheGebühr(REPO repository, int art, String buchungsText,Konto betragsKonto,Konto gebührenKonto) {
        super(repository, art, buchungsText);
        this.betragsKonto = betragsKonto;
        this.gebührenKonto = gebührenKonto;
    }

    protected abstract MonetaryAmount getGebühr(REPO repository, Abrechnung abrechnung,MonetaryAmount betrag);
        
    
    @Override
    protected Werte getWerte(REPO repository, Abrechnung abrechnung) {
        MonetaryAmount betrag = repository.getBetrag();
        MonetaryAmount gebühr = getGebühr(repository, abrechnung,betrag);
        double mwstSatz = repository.getMwstSatz(abrechnung);
        MonetaryAmount mwst = Geld.percentAmount(gebühr,mwstSatz);

        Werte w = new Werte();
        w.put(betragsKonto, betrag);
        w.put(gebührenKonto, gebühr.negate());
        w.put(repository.getMwstKonto(abrechnung), mwst.negate());
        return w;
    }
}