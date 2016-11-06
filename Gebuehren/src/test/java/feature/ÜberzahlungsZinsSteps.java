package feature;

import javax.money.MonetaryAmount;

import org.junit.Test;

import repositories.TestProzentualRepository;
import repositories.Test�berzahlungsRepository;
import test.TestKonto;
import �berzahlungen.�berzahlungsZins;
import static org.junit.Assert.*;
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


public class �berzahlungsZinsSteps {
    public enum Arten {
        GEB�HR
    }
    
    Konto zinsKonto = new TestKonto(2, "Zins");
    Konto �berzahlungKonto = new TestKonto(3, "�berzahlung aus dem Vormonat");
    Test�berzahlungsRepository repo = new Test�berzahlungsRepository();
    Abrechnung abrechnung;
    
   
    public �berzahlungsZinsSteps() {
        super();
        abrechnung = new TestAbrechnung(1,repo);
        System.out.println(getClass().getName());
    }

    @Angenommen("^der Zins f�r �berzahlungen ist (\\-{0,1}\\d+) %$")
    public void der_zinssatz_ist(double zins) throws Throwable {
        repo.set�berzahlungsZins(zins/100.0);
    }

    @Und("^die Untergrenze f�r die Berechnung von Zinsen ist (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void die_Untergrenze_f�r_Betr�ge_ist(long betrag) throws Throwable {
        repo.setUntergrenzeF�rZinsberechnung(Geld.createAmount(betrag));
    }
    
    @Und("Zinsen unter (\\-{0,1}\\d+\\,{0,1}\\d*) werden nicht ber�cksichtigt$")
     public void der_minimale_Zins_ist(long betrag) throws Throwable {
        repo.setMinimaler�berzahlungsZins(Geld.createAmount(betrag));
    }

    @Und("^die �berzahlung ist (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void die_�berzahlung_ist(long betrag) throws Throwable {
        Werte w = new Werte();
        w.putIfAbsent(�berzahlungKonto,Geld.createAmount(betrag));
        repo.setAktuelleWerte(w);
    }


    @Dann("^ist der Zins (\\-{0,1}\\d+\\,{0,1}\\d*)$")
    public void dann_ist_der_Zins(double dergebnis) throws Throwable {
        �berzahlungsZins zins = new �berzahlungsZins(repo,�berzahlungKonto, zinsKonto,Test�berzahlungsRepository.Art.�BERZAHLUNG_AUS_DEM_VORMONAT,Test�berzahlungsRepository.Art.�BERZAHLUNGS_ZINS);
        Werte w = zins.getWerte(abrechnung);
        MonetaryAmount berechnet = w.get(zinsKonto);
        MonetaryAmount ergebnis = Geld.createAmount(dergebnis).negate();
        assertEquals(ergebnis,berechnet);
    }

}
