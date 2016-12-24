package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import repositories.TestRepository;
import ¸berzahlungen.‹berzahlung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import entities.TestAbrechnung;

public class ‹berzahlungsTest {
    public enum Art {
        ‹BERZAHLUNG, ‹BERTRAG
    }

    Konto ¸berzahlungsKonto = new TestKonto(2, "‹berzahlung");
    Konto ¸bertragKonto = new TestKonto(2, "‹bertag");
    TestRepository repo = new TestRepository();
    TestAbrechnung abrechnung;

    public ‹berzahlungsTest() {
        abrechnung = new TestAbrechnung(1, repo);
    }

    public void testen(double neuesSaldo, double alte‹berzahlung,
            double erwartete‹berzahlung) {
        ‹berzahlung ¸berzahlung = new ‹berzahlung(Art.‹BERZAHLUNG,
                ¸berzahlungsKonto, abrechnung);
        abrechnung.setSaldo(Geld.createAmount(neuesSaldo));
        repo.setAktuelleWerte(createWerte(¸berzahlungsKonto, alte‹berzahlung));
        Bewegungen w = ¸berzahlung.getBewegungen();
        Bewegungen erwartet = createWerte(¸berzahlungsKonto,
                erwartete‹berzahlung);
        assertEquals(erwartet, w);
    }

    private Bewegungen createWerte(Konto konto, double betrag) {
        Bewegungen w = new Bewegungen();
        if (Math.abs(betrag) >= 0.004) {
            w.put(konto, Geld.createAmount(betrag));
        }
        return w;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 0.0, 100.0, 100.0 },
                { 100.0, 100.0, 0.0 }, { -100.0, 100.0, 200.0 },
                { -100.0, 0.0, 100.0 }, });
    }

    @Parameter(value = 0)
    public double neuesSaldo;

    @Parameter(value = 1)
    public double alte‹berzahlung;

    @Parameter(value = 2)
    public double erwartete‹berzahlung;

    @Test
    public void test() {
        testen(neuesSaldo, alte‹berzahlung, erwartete‹berzahlung);
    }

}
