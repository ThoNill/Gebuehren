package beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.money.MonetaryAmount;

public class Werte extends HashMap<Konto, MonetaryAmount> {

    public Werte differenz(Werte b) {
        Werte dWerte = new Werte();
        Set<Konto> alle = new HashSet<>();
        alle.addAll(this.keySet());
        alle.addAll(b.keySet());
        for (Konto k : alle) {
            MonetaryAmount d = differenz(k, b);
            if (!d.isZero() ) {
                dWerte.put(k, d);
            }
        }
        return dWerte;
    }

    public MonetaryAmount summe(MonetaryAmount startWert) {
        MonetaryAmount summe = startWert;
        for (Konto k : this.keySet()) {
            MonetaryAmount v = get(k);
            summe = summe.add(v);
        }
        return summe;
    }

    public MonetaryAmount summe() {
        return summe(Geld.createAmount(0));
    }

    private MonetaryAmount differenz(Konto k, Werte b) {
        MonetaryAmount ga = get(k);
        MonetaryAmount gb = b.get(k);
        return ga.subtract(gb);
    }

    public MonetaryAmount get(Konto konto) {
        MonetaryAmount amount = super.get(konto);
        if (amount == null) {
            return Geld.getNull();
        }
        return amount;
    }

    public MonetaryAmount put(Konto konto, MonetaryAmount amount) {
        if (konto.hasErgänzung()) {
            Werte werte = konto.ergänzen(amount);
            for (Konto k : werte.keySet()) {
                MonetaryAmount a = werte.get(k);
                super.put(k, a);
            }
        } else {
            super.put(konto, amount);
        }
        return amount;
    }

    @Override
    public String toString() {
        return "Werte [entrySet=" + entrySet() + "]";
    }

    public Werte add(Werte werte) {
        Werte summe = new Werte();
        Set<Konto> alle = new HashSet<>();
        alle.addAll(this.keySet());
        alle.addAll(werte.keySet());
        for (Konto k : alle) {
            MonetaryAmount d = summe(k, werte);
            if (!d.isZero() ) {
                summe.put(k, d);
            }
        }
        return summe;
    }
    
    private MonetaryAmount summe(Konto k, Werte b) {
        MonetaryAmount ga = get(k);
        MonetaryAmount gb = b.get(k);
        return ga.add(gb);
    }
}
