package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import test.TestKonto;
import test.TestRepository;
import static org.junit.Assert.*;
import gebuehren.ProzentualeGeb�hr;
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


public class Prozentual {
    Konto geb�hrKonto = new TestKonto(2, "Geb�hr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;
    
   
    public Prozentual() {
        super();
        abrechnung = new Abrechnung(repo);
    }

    @Angenommen("^die prozentuale Geb�hr ist (\\d+) %$")
    public void i_have_a_calculator(double prozentsatz) throws Throwable {
        repo.setProzentsatz(prozentsatz/100.0);
    }

    @Und("^der Betrag ist (\\d+\\,{0,1}\\d+)$")
    public void i_add_and(long betrag) throws Throwable {
        repo.setBetrag(Geld.createAmount(betrag));
    }

    @Dann("^ist die Geb�hr (\\d+\\,{0,1}\\d+)$")
    public void the_result_should_be(double dergebnis) throws Throwable {
        ProzentualeGeb�hr geb�hr = new ProzentualeGeb�hr(repo,  10,
                "Geb�hr",betragKonto,geb�hrKonto);
        Werte w = geb�hr.getWerte(abrechnung);
        MonetaryAmount berechnet = w.get(geb�hrKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
