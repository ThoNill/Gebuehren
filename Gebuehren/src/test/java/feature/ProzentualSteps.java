package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import repositories.TestProzentualRepository;
import test.TestKonto;
import static org.junit.Assert.*;
import gebühren.ProzentualeGebühr;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import cucumber.api.java.de.Und;
import cucumber.api.PendingException;
import entities.TestAbrechnung;


public class ProzentualSteps {
    public enum Arten {
        GEBÜHR
    }
    
    Konto gebührKonto = new TestKonto(2, "Gebühr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestProzentualRepository repo = new TestProzentualRepository();
    Abrechnung abrechnung;
    
   
    public ProzentualSteps() {
        super();
        abrechnung = new TestAbrechnung(1,repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^die prozentuale Gebühr ist (\\-{0,1}\\d+) %$")
    public void die_prozentuale_Gebühr_ist(double prozentsatz) throws Throwable {
        repo.setProzentsatz(prozentsatz/100.0);
    }

    @Und("^der Betrag ist (\\-{0,1}\\d+\\,{0,1}\\d+)$")
    public void der_Betrag_ist(long betrag) throws Throwable {
        repo.setBetrag(Geld.createAmount(betrag));
    }

    @Dann("^ist die Gebühr (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_die_Gebühr(double dergebnis) throws Throwable {
        ProzentualeGebühr gebühr = new ProzentualeGebühr(repo, Arten.GEBÜHR,
                "Gebühr",betragKonto,gebührKonto);
        Bewegungen w = gebühr.getBewegungen(abrechnung);
        MonetaryAmount berechnet = w.get(gebührKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
