package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import entities.TestAbrechnung;
import geb�hren.ProzentualeGeb�hr;
import repositories.TestProzentualRepository;
import repositories.TestRepository;
import test.Geb�hrenTest.Arten;
import �berzahlungen.�berzahlung;
import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Bewegungen;

public class �berzahlungsTest {
    public enum Art {
        �BERZAHLUNG,�BERTRAG
    }
    Konto �berzahlungsKonto = new TestKonto(2, "�berzahlung");
    Konto �bertragKonto = new TestKonto(2, "�bertag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;

    public �berzahlungsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }
    
    public void testen(double neuesSaldo,double alte�berzahlung,double erwartete�berzahlung) {
        �berzahlung �berzahlung = new �berzahlung(repo, Art.�BERZAHLUNG,�berzahlungsKonto);
        repo.setSaldo(Geld.createAmount(neuesSaldo));
        repo.setAktuelleWerte(createWerte(�berzahlungsKonto,alte�berzahlung));
        Bewegungen w = �berzahlung.getWerte(abrechnung);
        Bewegungen erwartet = createWerte(�berzahlungsKonto,erwartete�berzahlung);
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
    public double alte�berzahlung;
    
    @Parameter(value = 2)
    public double erwartete�berzahlung;

    @Test
    public void test() {
        testen(neuesSaldo,alte�berzahlung,erwartete�berzahlung);
    }

}
