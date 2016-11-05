package beans.impl;

import java.io.ObjectOutputStream.PutField;

import javax.money.MonetaryAmount;

import beans.Geld;
import beans.Konto;
import beans.Werte;

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
    public Werte ergänzen(MonetaryAmount amount) {
        Werte w = new Werte();
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
