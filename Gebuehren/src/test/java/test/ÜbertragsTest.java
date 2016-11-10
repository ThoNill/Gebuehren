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
import überzahlungen.ÜberzahlungsÜbertrag;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public class ÜbertragsTest {
    public enum Art {
        ÜBERZAHLUNG,ÜBERTRAG
    }
    Konto überzahlungsKonto = new TestKonto(2, "Überzahlung");
    Konto übertragKonto = new TestKonto(2, "Übertag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;

    public ÜbertragsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }
    
    public void testen(double alteÜberzahlung,double erwarteterÜbertrag) {
        ÜberzahlungsÜbertrag übertrag = new ÜberzahlungsÜbertrag(repo, Art.ÜBERZAHLUNG,überzahlungsKonto,Art.ÜBERTRAG,übertragKonto);
        repo.setAktuelleWerte(createWerte(überzahlungsKonto,alteÜberzahlung));
        Bewegungen w = übertrag.getBewegungen(abrechnung);
        Bewegungen erwartet = createWerte(überzahlungsKonto,erwarteterÜbertrag);
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
    public double alteÜberzahlung;
    
    @Parameter(value = 1)
    public double erwarteterÜbertrag;

    @Test
    public void test() {
        testen(alteÜberzahlung,erwarteterÜbertrag);
    }

}
