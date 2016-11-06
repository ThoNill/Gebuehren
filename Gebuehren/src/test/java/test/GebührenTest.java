package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import entities.TestAbrechnung;
import repositories.TestProzentualRepository;
import geb�hren.Geb�hr;
import geb�hren.ProzentualRepository;
import geb�hren.ProzentualeGeb�hr;
import abrechnung.Abrechnung;
import abrechnung.Repository;
import beans.Geld;
import beans.Konto;
import beans.Werte;

@RunWith(org.junit.runners.Parameterized.class)
public class Geb�hrenTest {
    public enum Arten {
        GEB�HR
    }

    Konto geb�hrKonto = new TestKonto(2, "Geb�hr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestProzentualRepository repo = new TestProzentualRepository();
    Abrechnung abrechnung;

    public Geb�hrenTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }

    public void prozentualeGeb�hr(long betrag, long erwarteteGeb�hr, long mwst,double prozentsatz) {
        ProzentualeGeb�hr geb�hr = new ProzentualeGeb�hr(repo, Arten.GEB�HR,
                "Geb�hr",betragKonto,geb�hrKonto);
        repo.setBetrag(betrag);
        repo.setProzentsatz(prozentsatz);
        Werte w = geb�hr.getWerte(abrechnung);
        Werte erwartet = getErwarteteWerte(betrag, erwarteteGeb�hr, mwst);
        assertEquals(erwartet, w);
    }

    private Werte getErwarteteWerte(long betrag, long geb�hr, long mwst) {
        Werte w = new Werte();
        w.put(betragKonto, Geld.createAmount(betrag / 100.0));
        w.put(geb�hrKonto, Geld.createAmount(geb�hr / 100.0).negate());
        w.put(repo.getMwstKonto(abrechnung), Geld.createAmount(mwst / 100.0).negate());
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
    public long geb�hr;

    @Parameter(value = 2)
    public long mwst;
    
    @Parameter(value = 3)
    public double prozentsatz;

    @Test
    public void test() {
        prozentualeGeb�hr(betrag, geb�hr, mwst,prozentsatz);
    }

}
