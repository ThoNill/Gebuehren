package gebühren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.AutoMwstKonto;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class EinfacheGebühr<REPO extends MarkierendesRepository> extends  Gebühr<REPO> {
    private Abrechnung abrechnung;
    protected Konto gebührenKonto;

    public EinfacheGebühr(REPO repository, Enum art, String buchungsText,Konto gebührenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText);
        this.gebührenKonto = gebührenKonto;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return abrechnung;
    }

    @Override
    protected Bewegungen getWerte(REPO repository) {
      
        MonetaryAmount gebühr = getGebühr(repository);
        Konto gKonto = holeGebührenKonto(repository);
        
        Bewegungen w = new Bewegungen();
        w.put(gKonto, gebühr.negate());
        return w;
    }

    protected Konto holeGebührenKonto(REPO repository) {
        double mwstSatz = abrechnung.getMwstSatz();
        Konto gKonto = new AutoMwstKonto(gebührenKonto, abrechnung.getMwstKonto(), mwstSatz);
        return gKonto;
    }
    
    protected abstract MonetaryAmount getGebühr(REPO repository) ;

}