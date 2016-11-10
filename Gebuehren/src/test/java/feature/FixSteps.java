package feature;

import static org.junit.Assert.assertEquals;

import javax.money.MonetaryAmount;

import repositories.TestFixeGeb�rRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import entities.TestAbrechnung;
import geb�hren.FixeGeb�hr;


public class FixSteps {
    public enum Arten {
        GEB�HR
    }
    
    Konto geb�hrKonto = new TestKonto(2, "Geb�hr");
    TestFixeGeb�rRepository repo = new TestFixeGeb�rRepository();
    Abrechnung abrechnung;
    
   
    public FixSteps() {
        super();
        abrechnung = new TestAbrechnung(1,repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^die fixe Geb�hr ist (\\-{0,1}\\d+)$")
    public void die_fixe_Geb�hr_ist(double dergebnis) throws Throwable {
        repo.setGeb�hr(Geld.createAmount(dergebnis));
    }

    @Dann("^ist die Geb�hr eben (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_die_Geb�hr_eben(double dergebnis) throws Throwable {
        FixeGeb�hr geb�hr = new FixeGeb�hr(repo, Arten.GEB�HR,
                "Geb�hr",geb�hrKonto,abrechnung);
        Bewegungen w = geb�hr.getBewegungen();
        MonetaryAmount berechnet = w.get(geb�hrKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
