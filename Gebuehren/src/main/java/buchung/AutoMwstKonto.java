package buchung;

import javax.money.MonetaryAmount;

import betrag.Geld;

public class AutoMwstKonto implements Konto {
    private Konto original;
    private Konto mwst;
    private double mwstSatz ;
    
    public AutoMwstKonto(Konto original, Konto mwst, double mwstSatz) {
        super();
        this.original = original;
        this.mwst = mwst;
        this.mwstSatz = mwstSatz;
    }
 
    @Override
    public boolean hasErgänzung() {
        return true;
    }
    
    @Override
    public Bewegungen ergänzen(MonetaryAmount amount) {
        Bewegungen w = new Bewegungen();
        w.put(original,amount);
        MonetaryAmount mwst = Geld.percentAmount(amount,mwstSatz);
        w.put(this.mwst,mwst);
        return w;
    }


    @Override
    public int getNummer() {
        return original.getNummer();
    }


    @Override
    public String getName() {
        return original.getName();
    }
    
   

}
