package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import repositories.TestRepositoryMitBuchungsAufträgen;
import überzahlungen.Überzahlung;
import abrechnung.AbrechnungsAblauf;
import betrag.Geld;
import buchung.Konto;
import entities.TestAbrechnung;
import gebühren.ProzentualeGebühr;

public class AbrechnungsTest {
    public enum Arten {
        GEBÜHR,ÜBERZAHLUNG
    }

    Konto gebührKonto = new TestKonto(2, "Gebühr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    Konto überzahlung = new TestKonto(4, "Überzahlung");
    
    TestRepositoryMitBuchungsAufträgen repo = new TestRepositoryMitBuchungsAufträgen(200.0);
    TestAbrechnung abrechnung;
    AbrechnungsAblauf ablauf;
    
    public AbrechnungsTest() {
        abrechnung = new TestAbrechnung(1,repo);
        ablauf = new AbrechnungsAblauf();
    }

    @Test
    public void testen() {
        ablauf.add(new ProzentualeGebühr(repo, Arten.GEBÜHR,"Test",betragKonto,gebührKonto,abrechnung));
        ablauf.add(new Überzahlung(Arten.ÜBERZAHLUNG, überzahlung,abrechnung));
        
        ablauf.abrechnen();
        
        System.out.println(repo.saldo());
        
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGebührenProzentsatz()),repo.getAktuelleWerte(gebührKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(überzahlung));
        
        ablauf.abrechnen();
 
        assertEquals(Geld.createAmount(200.0),repo.getAktuelleWerte(betragKonto));
        assertEquals(Geld.createAmount(-200.0 * repo.getGebührenProzentsatz()),repo.getAktuelleWerte(gebührKonto));
        assertEquals(Geld.createAmount(0.0),repo.getAktuelleWerte(überzahlung));
        
    }
}
