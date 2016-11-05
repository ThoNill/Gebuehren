package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import repositories.TestProzentualRepository;
import repositories.TestÜberzahlungsRepository;
import test.TestKonto;
import überzahlungen.ÜberzahlungsZins;
import static org.junit.Assert.*;
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


public class ÜberzahlungsZinsSteps {
    public enum Arten {
        GEBÜHR
    }
    
    Konto zinsKonto = new TestKonto(2, "Zins");
    Konto überzahlungKonto = new TestKonto(3, "Überzahlung aus dem Vormonat");
    TestÜberzahlungsRepository repo = new TestÜberzahlungsRepository();
    Abrechnung abrechnung;
    
   
    public ÜberzahlungsZinsSteps() {
        super();
        abrechnung = new Abrechnung(repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^der Zins für Überzahlungen ist (\\-{0,1}\\d+) %$")
    public void der_zinssatz_ist(double zins) throws Throwable {
        repo.setÜberzahlungsZins(zins/100.0);
    }

    @Und("^die Untergrenze für die Berechnung von Zinsen ist (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void die_Untergrenze_für_Beträge_ist(long betrag) throws Throwable {
        repo.setUntergrenzeFürZinsberechnung(Geld.createAmount(betrag));
    }
    
    @Und("Zinsen unter (\\-{0,1}\\d+\\,{0,1}\\d*) werden nicht berücksichtigt$")
     public void der_minimale_Zins_ist(long betrag) throws Throwable {
        repo.setMinimalerÜberzahlungsZins(Geld.createAmount(betrag));
    }

    @Und("^die Überzahlung ist (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void die_Überzahlung_ist(long betrag) throws Throwable {
        Werte w = new Werte();
        w.putIfAbsent(überzahlungKonto,Geld.createAmount(betrag));
        repo.setAktuelleWerte(w);
    }


    @Dann("^ist der Zins (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_der_Zins(double dergebnis) throws Throwable {
        ÜberzahlungsZins zins = new ÜberzahlungsZins(repo,überzahlungKonto, zinsKonto,TestÜberzahlungsRepository.Art.ÜBERZAHLUNG_AUS_DEM_VORMONAT,TestÜberzahlungsRepository.Art.ÜBERZAHLUNGS_ZINS);
        Werte w = zins.getWerte(abrechnung);
        MonetaryAmount berechnet = w.get(zinsKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
