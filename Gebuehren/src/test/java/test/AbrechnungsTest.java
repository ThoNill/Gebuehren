package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import repositories.TestRepositoryMitBuchungsAuftr�gen;
import �berzahlungen.�berzahlung;
import abrechnung.AbrechnungsAblauf;
import betrag.Geld;
import buchung.Konto;
import entities.TestAbrechnung;
import geb�hren.ProzentualeGeb�hr;

public class AbrechnungsTest {
    public enum Arten {
        GEB�HR,�BERZAHLUNG
    }

    Konto geb�hrKonto = new TestKonto(2, "Geb�hr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    Konto �berzahlung = new TestKonto(4, "�berzahlung");
    
    TestRepositoryMitBuchungsAuftr�gen repo = new TestRepositoryMitBuchungsAuftr�gen(200.0);
    TestAbrechnung abrechnung;
    AbrechnungsAblauf ablauf;
    
    public AbrechnungsTest() {
        abrechnung = new TestAbrechnung(1,repo);
        ablauf = new AbrechnungsAblauf();
    }

    @Test
    public void testen() {
        ablauf.add(new ProzentualeGeb�hr(repo, Arten.GEB�HR,"Test",betragKonto,geb�hrKonto,abrechnung));
        ablauf.add(new �berzahlung(Arten.�BERZAHLUNG, �berzahlung,abrechnung));
        
        ablauf.abrechnen();
        
        System.out.println(repo.saldo());
        
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGeb�hrenProzentsatz()),repo.getAktuelleWerte(geb�hrKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(�berzahlung));
        
        ablauf.abrechnen();
 
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGeb�hrenProzentsatz()),repo.getAktuelleWerte(geb�hrKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(�berzahlung));
        
    }
}
