package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import repositories.TestFixeGeb�rRepository;
import repositories.TestProzentualRepository;
import test.TestKonto;
import static org.junit.Assert.*;
import geb�hren.FixeGeb�hr;
import geb�hren.ProzentualeGeb�hr;
import abrechnung.Abrechnung;
import beans.Geld;
import beans.Konto;
import beans.Werte;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import cucumber.api.java.de.Und;
import cucumber.api.PendingException;
import entities.TestAbrechnung;


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
                "Geb�hr",geb�hrKonto);
        Werte w = geb�hr.getWerte(abrechnung);
        MonetaryAmount berechnet = w.get(geb�hrKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
