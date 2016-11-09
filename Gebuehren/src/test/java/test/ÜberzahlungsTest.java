package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import entities.TestAbrechnung;
import gebühren.ProzentualeGebühr;
import repositories.TestProzentualRepository;
import repositories.TestRepository;
import test.GebührenTest.Arten;
import überzahlungen.Überzahlung;
import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Bewegungen;

public class ÜberzahlungsTest {
    public enum Art {
        ÜBERZAHLUNG,ÜBERTRAG
    }
    Konto überzahlungsKonto = new TestKonto(2, "Überzahlung");
    Konto übertragKonto = new TestKonto(2, "Übertag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;

    public ÜberzahlungsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }
    
    public void testen(double neuesSaldo,double alteÜberzahlung,double erwarteteÜberzahlung) {
        Überzahlung überzahlung = new Überzahlung(repo, Art.ÜBERZAHLUNG,überzahlungsKonto);
        repo.setSaldo(Geld.createAmount(neuesSaldo));
        repo.setAktuelleWerte(createWerte(überzahlungsKonto,alteÜberzahlung));
        Bewegungen w = überzahlung.getWerte(abrechnung);
        Bewegungen erwartet = createWerte(überzahlungsKonto,erwarteteÜberzahlung);
        assertEquals(erwartet, w);
    }

    
    private Bewegungen createWerte(Konto konto,double betrag) {
        Bewegungen w = new Bewegungen();
        if(betrag != 0.0) {
            w.put(konto,Geld.createAmount(betrag));
        }
        return w;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                { 0.0, 100.0, 100.0 },
                { 100.0, 100.0, 0.0 },
                { -100.0, 100.0, 200.0 },
                { -100.0, 0.0, 100.0 },
        });
    }

    @Parameter(value = 0)
    public double neuesSaldo;

    @Parameter(value = 1)
    public double alteÜberzahlung;
    
    @Parameter(value = 2)
    public double erwarteteÜberzahlung;

    @Test
    public void test() {
        testen(neuesSaldo,alteÜberzahlung,erwarteteÜberzahlung);
    }

}
