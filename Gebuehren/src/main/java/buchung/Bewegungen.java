package buchung;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.money.MonetaryAmount;

import betrag.Geld;

/**
 * 
 * @author Thomas Nill
 * 
 * Verwaltung von Geldbeträgen, die auf unterschiedlichen Konten {@link Konto} liegen.
 *
 */
public class Bewegungen extends HashMap<Konto, MonetaryAmount> {

    public MonetaryAmount get(Konto konto) {
        MonetaryAmount amount = super.get(konto);
        if (amount == null) {
            return Geld.getNull();
        }
        return amount;
    }

    @Override
    public MonetaryAmount put(Konto konto, MonetaryAmount amount) {
        if (konto.hasErgänzung()) {
            Bewegungen bewegungen = konto.ergänzen(amount);
            for (Konto k : bewegungen.keySet()) {
                MonetaryAmount a = bewegungen.get(k);
                super.put(k, a);
            }
        } else {
            super.put(konto, amount);
        }
        return amount;
    }

    public MonetaryAmount add(Konto konto, MonetaryAmount betrag) {
        MonetaryAmount amount = get(konto);
        return put(konto, amount.add(betrag));
    }

    public Bewegungen differenz(Bewegungen b) {
        Set<Konto> alle = alleKeys(b);

        Bewegungen dWerte = new Bewegungen();
        for (Konto k : alle) {
            MonetaryAmount d = differenz(k, b);
            if (!d.isZero()) {
                dWerte.put(k, d);
            }
        }
        return dWerte;
    }

    private MonetaryAmount differenz(Konto k, Bewegungen b) {
        MonetaryAmount ga = get(k);
        MonetaryAmount gb = b.get(k);
        return ga.subtract(gb);
    }

    public Bewegungen add(Bewegungen bewegungen) {
        Set<Konto> alle = alleKeys(bewegungen);

        Bewegungen summe = new Bewegungen();
        for (Konto k : alle) {
            MonetaryAmount d = summe(k, bewegungen);
            if (!d.isZero()) {
                summe.put(k, d);
            }
        }
        return summe;
    }

    private MonetaryAmount summe(Konto k, Bewegungen b) {
        MonetaryAmount ga = get(k);
        MonetaryAmount gb = b.get(k);
        return ga.add(gb);
    }

    private Set<Konto> alleKeys(Bewegungen b) {
        Set<Konto> alle = new HashSet<>();
        alle.addAll(this.keySet());
        alle.addAll(b.keySet());
        return alle;
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

    @Override
    public String toString() {
        return "Werte [entrySet=" + entrySet() + "]";
    }

}
