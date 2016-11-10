package feature;

import static org.junit.Assert.assertEquals;

import javax.money.MonetaryAmount;

import repositories.TestFixeGebürRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import entities.TestAbrechnung;
import gebühren.FixeGebühr;


public class FixSteps {
    public enum Arten {
        GEBÜHR
    }
    
    Konto gebührKonto = new TestKonto(2, "Gebühr");
    TestFixeGebürRepository repo = new TestFixeGebürRepository();
    Abrechnung abrechnung;
    
   
    public FixSteps() {
        super();
        abrechnung = new TestAbrechnung(1,repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^die fixe Gebühr ist (\\-{0,1}\\d+)$")
    public void die_fixe_Gebühr_ist(double dergebnis) throws Throwable {
        repo.setGebühr(Geld.createAmount(dergebnis));
    }

    @Dann("^ist die Gebühr eben (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_die_Gebühr_eben(double dergebnis) throws Throwable {
        FixeGebühr gebühr = new FixeGebühr(repo, Arten.GEBÜHR,
                "Gebühr",gebührKonto,abrechnung);
        Bewegungen w = gebühr.getBewegungen();
        MonetaryAmount berechnet = w.get(gebührKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
