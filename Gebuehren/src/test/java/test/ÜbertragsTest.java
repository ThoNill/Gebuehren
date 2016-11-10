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
import �berzahlungen.�berzahlungs�bertrag;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public class �bertragsTest {
    public enum Art {
        �BERZAHLUNG,�BERTRAG
    }
    Konto �berzahlungsKonto = new TestKonto(2, "�berzahlung");
    Konto �bertragKonto = new TestKonto(2, "�bertag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;

    public �bertragsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }
    
    public void testen(double alte�berzahlung,double erwarteter�bertrag) {
        �berzahlungs�bertrag �bertrag = new �berzahlungs�bertrag(repo, Art.�BERZAHLUNG,�berzahlungsKonto,Art.�BERTRAG,�bertragKonto);
        repo.setAktuelleWerte(createWerte(�berzahlungsKonto,alte�berzahlung));
        Bewegungen w = �bertrag.getBewegungen(abrechnung);
        Bewegungen erwartet = createWerte(�berzahlungsKonto,erwarteter�bertrag);
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
                { 100.0, -100.0 },
                { 0.0, 0.0 },
                { -100.0, 100.0 }
        });
    }

    @Parameter(value = 0)
    public double alte�berzahlung;
    
    @Parameter(value = 1)
    public double erwarteter�bertrag;

    @Test
    public void test() {
        testen(alte�berzahlung,erwarteter�bertrag);
    }

}
