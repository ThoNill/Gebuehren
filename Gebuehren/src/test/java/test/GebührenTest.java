package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import repositories.TestProzentualRepository;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import entities.TestAbrechnung;
import gebühren.ProzentualeGebühr;

@RunWith(org.junit.runners.Parameterized.class)
public class GebührenTest {
    public enum Arten {
        GEBÜHR
    }

    Konto gebührKonto = new TestKonto(2, "Gebühr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestProzentualRepository repo = new TestProzentualRepository();
    Abrechnung abrechnung;

    public GebührenTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }

    public void prozentualeGebühr(long betrag, long erwarteteGebühr, long mwst,double prozentsatz) {
        ProzentualeGebühr gebühr = new ProzentualeGebühr(repo, Arten.GEBÜHR,
                "Gebühr",betragKonto,gebührKonto,abrechnung);
        repo.setBetrag(betrag);
        repo.setProzentsatz(prozentsatz);
        Bewegungen w = gebühr.getBewegungen();
        Bewegungen erwartet = getErwarteteWerte(betrag, erwarteteGebühr, mwst);
        assertEquals(erwartet, w);
    }

    private Bewegungen getErwarteteWerte(long betrag, long gebühr, long mwst) {
        Bewegungen w = new Bewegungen();
        w.put(betragKonto, Geld.createAmount(betrag / 100.0));
        w.put(gebührKonto, Geld.createAmount(gebühr / 100.0).negate());
        w.put(abrechnung.getMwstKonto(), Geld.createAmount(mwst / 100.0).negate());
        return w;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                { 0, 0, 0, 0.12 },
                { 100, 12, (long)(12 * 0.19), 0.12 },
                { -100, -12, -(long)(12 * 0.19), 0.12 }
                
        });
    }

    @Parameter(value = 0)
    public long betrag;

    @Parameter(value = 1)
    public long gebühr;

    @Parameter(value = 2)
    public long mwst;
    
    @Parameter(value = 3)
    public double prozentsatz;

    @Test
    public void test() {
        prozentualeGebühr(betrag, gebühr, mwst,prozentsatz);
    }

}
