package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import repositories.TestFixeGebürRepository;
import repositories.TestProzentualRepository;
import test.TestKonto;
import static org.junit.Assert.*;
import gebühren.FixeGebühr;
import gebühren.ProzentualeGebühr;
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
                "Gebühr",gebührKonto);
        Werte w = gebühr.getWerte(abrechnung);
        MonetaryAmount berechnet = w.get(gebührKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
