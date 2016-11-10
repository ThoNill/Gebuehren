package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.TestAbrechnung;
import gebühren.ProzentualeGebühr;
import repositories.TestRepositoryMitBuchungsAufträgen;
import überzahlungen.Überzahlung;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Konto;

public class AbrechnungsTest {
    public enum Arten {
        GEBÜHR,ÜBERZAHLUNG
    }

    Konto gebührKonto = new TestKonto(2, "Gebühr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    Konto überzahlung = new TestKonto(4, "Überzahlung");
    
    TestRepositoryMitBuchungsAufträgen repo = new TestRepositoryMitBuchungsAufträgen(200.0);
    Abrechnung abrechnung;

    public AbrechnungsTest() {
        abrechnung = new TestAbrechnung(1,repo);
    }

    @Test
    public void testen() {
        abrechnung.add(new ProzentualeGebühr(repo, Arten.GEBÜHR,"Test",betragKonto,gebührKonto));
        abrechnung.add(new Überzahlung(repo, Arten.ÜBERZAHLUNG, überzahlung));
        
        abrechnung.abrechnen();
        
        System.out.println(repo.saldo(abrechnung));
        
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGebührenProzentsatz()),repo.getAktuelleWerte(gebührKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(überzahlung));
        
        abrechnung.abrechnen();
 
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGebührenProzentsatz()),repo.getAktuelleWerte(gebührKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(überzahlung));
        
    }
}
