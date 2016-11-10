package geb�hren;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import buchung.AutoMwstKonto;
import buchung.Bewegungen;
import buchung.Konto;

public abstract class EinfacheGeb�hr<REPO extends MarkierendesRepository> extends  Geb�hr<REPO> {
    private Abrechnung abrechnung;
    protected Konto geb�hrenKonto;

    public EinfacheGeb�hr(REPO repository, Enum art, String buchungsText,Konto geb�hrenKonto,Abrechnung abrechnung) {
        super(repository, art, buchungsText);
        this.geb�hrenKonto = geb�hrenKonto;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return abrechnung;
    }

    @Override
    protected Bewegungen getWerte(REPO repository) {
      
        MonetaryAmount geb�hr = getGeb�hr(repository);
        Konto gKonto = holeGeb�hrenKonto(repository);
        
        Bewegungen w = new Bewegungen();
        w.put(gKonto, geb�hr.negate());
        return w;
    }

    protected Konto holeGeb�hrenKonto(REPO repository) {
        double mwstSatz = abrechnung.getMwstSatz();
        Konto gKonto = new AutoMwstKonto(geb�hrenKonto, abrechnung.getMwstKonto(), mwstSatz);
        return gKonto;
    }
    
    protected abstract MonetaryAmount getGeb�hr(REPO repository) ;

}