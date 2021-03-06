package feature;

import static org.junit.Assert.assertEquals;

import javax.money.MonetaryAmount;

import repositories.TestProzentualRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import entities.TestAbrechnung;
import geb�hren.ProzentualeGeb�hr;


public class ProzentualSteps {
    public enum Arten {
        GEB�HR
    }
    
    Konto geb�hrKonto = new TestKonto(2, "Geb�hr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestProzentualRepository repo = new TestProzentualRepository();
    Abrechnung abrechnung;
    
   
    public ProzentualSteps() {
        super();
        abrechnung = new TestAbrechnung(1,repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^die prozentuale Geb�hr ist (\\-{0,1}\\d+) %$")
    public void die_prozentuale_Geb�hr_ist(double prozentsatz) {
        repo.setProzentsatz(prozentsatz/100.0);
    }

    @Und("^der Betrag ist (\\-{0,1}\\d+\\,{0,1}\\d+)$")
    public void der_Betrag_ist(long betrag) {
        repo.setBetrag(Geld.createAmount(betrag));
    }

    @Dann("^ist die Geb�hr (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_die_Geb�hr(double dergebnis)  {
        ProzentualeGeb�hr geb�hr = new ProzentualeGeb�hr(repo, Arten.GEB�HR,
                "Geb�hr",betragKonto,geb�hrKonto,abrechnung);
        Bewegungen w = geb�hr.getBewegungen();
        MonetaryAmount berechnet = w.get(geb�hrKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
