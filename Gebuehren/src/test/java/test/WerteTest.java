package test;

import static org.junit.Assert.*;

import javax.money.MonetaryAmount;

import org.junit.Test;

import static beans.Geld.*;
import beans.Konto;
import beans.Bewegungen;
import beans.impl.AutoMwstKonto;

public class WerteTest {

    @Test
    public void test1() {
        Konto k1 = new TestKonto(1001, "Gebuehr");
        Konto k2 = new TestKonto(1002, "Mwst");
        Konto k3 = new TestKonto(1003, "Pauschale");

        Bewegungen bewegungen = new Bewegungen();
        bewegungen.put(k1, createAmount(3));
        bewegungen.put(k2, createAmount(4));
        bewegungen.put(k3, createAmount(5));

        MonetaryAmount summe = bewegungen.summe(createAmount(0L));

        assertEquals(3L + 4L + 5L, summe.getNumber().longValue());
        
        summe = bewegungen.summe();

        assertEquals(3L + 4L + 5L, summe.getNumber().longValue());

    }

    @Test
    public void test2() {
        Konto k1 = new TestKonto(1001, "Gebuehr");
        Konto k2 = new TestKonto(1002, "Mwst");
        Konto k3 = new TestKonto(1003, "Pauschale");

        Bewegungen werte1 = new Bewegungen();
        werte1.put(k1, createAmount(3));
        werte1.put(k2, createAmount(4));
        werte1.put(k3, createAmount(5));

        Bewegungen werte2 = new Bewegungen();
        werte2.put(k1, createAmount(2));
        werte2.put(k2, createAmount(3));
        werte2.put(k3, createAmount(4));

        Bewegungen werte3 = werte1.differenz(werte2);

        for (Konto k : werte3.keySet()) {
            MonetaryAmount v = werte3.get(k);
            if (v != null) {
                assertEquals(v.getNumber().longValue(), 1L);
            }
        }

    }

    @Test
    public void test3() {
        Konto k1 = new TestKonto(1001, "Gebuehr");
        Konto k2 = new TestKonto(1002, "Mwst");
        AutoMwstKonto ak = new AutoMwstKonto(k1, k2, 0.19);

        Bewegungen werte1 = new Bewegungen();
        werte1.put(k1, createAmount(100));
        werte1.put(k2, createAmount(19));

        Bewegungen werte2 = new Bewegungen();
        werte2.put(ak, createAmount(100));

        Bewegungen werte3 = werte1.differenz(werte2);

        assertEquals(0, werte3.keySet().size());

    }

    @Test
    public void runden() {
        for (long l = 0; l < 100; l++) {
            rundenTesten(l);
        }
        runden(1.46, 1.456);
    }

    public void rundenTesten(long l) {
        runden(((double) l) / 100,
                Double.parseDouble("0." + ((l < 10) ? "0" : "") + l));
    }

    protected void runden(double expected, double value) {
        assertEquals(expected, createAmount(value).getNumber().doubleValue(),
                0.001);
    }

}
