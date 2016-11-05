package gebühren;

import javax.money.MonetaryAmount;

import beans.Konto;
import beans.Werte;
import beans.impl.AutoMwstKonto;
import abrechnung.Abrechnung;
import abrechnung.Repository;

public abstract class EinfacheGebühr<REPO extends Repository> extends  Gebühr<REPO> {

    protected Konto gebührenKonto;

    public EinfacheGebühr(REPO repository, Enum art, String buchungsText,Konto gebührenKonto) {
        super(repository, art, buchungsText);
        this.gebührenKonto = gebührenKonto;
    }

    @Override
    protected Werte getWerte(REPO repository, Abrechnung abrechnung) {
      
        MonetaryAmount gebühr = getGebühr(repository,abrechnung);
        Konto gKonto = holeGebührenKonto(repository, abrechnung);
        
        Werte w = new Werte();
        w.put(gKonto, gebühr.negate());
        return w;
    }

    protected Konto holeGebührenKonto(REPO repository, Abrechnung abrechnung) {
        double mwstSatz = repository.getMwstSatz(abrechnung);
        Konto gKonto = new AutoMwstKonto(gebührenKonto, repository.getMwstKonto(abrechnung), mwstSatz);
        return gKonto;
    }
    
    protected abstract MonetaryAmount getGebühr(REPO repository,Abrechnung abrechnung) ;

}