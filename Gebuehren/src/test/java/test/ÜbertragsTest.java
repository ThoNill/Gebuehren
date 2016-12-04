package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import repositories.TestRepository;
import überzahlungen.ÜberzahlungsÜbertrag;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import entities.TestAbrechnung;

public class ÜbertragsTest {
    public enum Art {
        ÜBERZAHLUNG,ÜBERTRAG
    }
    Konto überzahlungsKonto = new TestKonto(2, "Überzahlung");
    Konto übertragKonto = new TestKonto(2, "Übertag");
    TestRepository repo = new TestRepository();
    TestAbrechnung abrechnung;

    public ÜbertragsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }
    
    public void testen(double alteÜberzahlung,double erwarteterÜbertrag) {
        ÜberzahlungsÜbertrag übertrag = new ÜberzahlungsÜbertrag(Art.ÜBERZAHLUNG,überzahlungsKonto,Art.ÜBERTRAG,übertragKonto,abrechnung);
        repo.setAktuelleWerte(createWerte(überzahlungsKonto,alteÜberzahlung));
        Bewegungen w = übertrag.getBewegungen();
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
