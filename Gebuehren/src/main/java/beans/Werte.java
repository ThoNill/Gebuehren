package beans;

import java.util.HashMap;

import javax.money.MonetaryAmount;

public class Werte extends HashMap<Konto, MonetaryAmount> {

    public Werte differenz(Werte b) {
        Werte dWerte = new Werte();
        for (Konto k : this.keySet()) {
            MonetaryAmount d = differenz(k, b);
            if (d != null) {
                dWerte.put(k, d);
            }
        }
        for (Konto k : b.keySet()) {
            if (!dWerte.containsKey(k)) {
                MonetaryAmount d = b.get(k);
                dWerte.put(k, d.negate());
            }
        }
        return dWerte;
    }


    public MonetaryAmount summe(MonetaryAmount startWert) {
        MonetaryAmount summe = startWert;
        for (Konto k : this.keySet()) {
            MonetaryAmount v = get(k);
            if (v != null) {
                summe = summe.add(v);
            }
        }
        return summe;
    }
    
    public MonetaryAmount summe() {
        return summe(Geld.createAmount(0));
    }
    
    private MonetaryAmount differenz(Konto k, Werte b) {
        MonetaryAmount ga = get(k);
        MonetaryAmount gb = b.get(k);
        
        if (ga != null) {
            if (gb != null) {
                return ga.subtract(gb);
            }
            return ga;
        }
        if (gb != null) {
            return gb.negate();
        }
        return null;
    }
}
