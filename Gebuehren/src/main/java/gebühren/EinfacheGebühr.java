package geb�hren;

import javax.money.MonetaryAmount;

import beans.Konto;
import beans.Werte;
import beans.impl.AutoMwstKonto;
import abrechnung.Abrechnung;
import abrechnung.Repository;

public abstract class EinfacheGeb�hr<REPO extends Repository> extends  Geb�hr<REPO> {

    protected Konto geb�hrenKonto;

    public EinfacheGeb�hr(REPO repository, Enum art, String buchungsText,Konto geb�hrenKonto) {
        super(repository, art, buchungsText);
        this.geb�hrenKonto = geb�hrenKonto;
    }

    @Override
    protected Werte getWerte(REPO repository, Abrechnung abrechnung) {
      
        MonetaryAmount geb�hr = getGeb�hr(repository,abrechnung);
        Konto gKonto = holeGeb�hrenKonto(repository, abrechnung);
        
        Werte w = new Werte();
        w.put(gKonto, geb�hr.negate());
        return w;
    }

    protected Konto holeGeb�hrenKonto(REPO repository, Abrechnung abrechnung) {
        double mwstSatz = repository.getMwstSatz(abrechnung);
        Konto gKonto = new AutoMwstKonto(geb�hrenKonto, repository.getMwstKonto(abrechnung), mwstSatz);
        return gKonto;
    }
    
    protected abstract MonetaryAmount getGeb�hr(REPO repository,Abrechnung abrechnung) ;

}