package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import betrag.Geld;
import buchung.BuchungsAuftrag;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        BebuchtTestEntität auftrag= new BebuchtTestEntität(BebuchtTestEntität.Art.AUFTRAG,10,Geld.createAmount(20),repository);

        assertEquals(1,repository.getBuchungen().size());
        
        assertEquals(Geld.createAmount(20),auftrag.getSoll());
        
        BuchungsAuftrag buchung = repository.getBuchungen().get(0);
        assertEquals(Geld.createAmount(20),buchung.getWerte().get(new TestKonto(20, "Soll")));
       
    }
    
    @Test
    public void testForderung() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        TestForderung auftrag= new TestForderung(TestForderung.Art.FORDERUNG,10,Geld.createAmount(20),repository);

        assertEquals(1,repository.getBuchungen().size());
        
        assertEquals(Geld.createAmount(20),auftrag.getSoll());
        
        BuchungsAuftrag buchung = repository.getBuchungen().get(0);
        assertEquals(Geld.createAmount(20),buchung.getWerte().get(new TestKonto(1, "Rest")));
       
        auftrag.bezahlen(repository,Geld.createAmount(5));
        assertEquals(Geld.createAmount(15),auftrag.getRest());
        assertEquals(Geld.createAmount(5),auftrag.getHaben());
        
    }
    
    @Test
    public void testForderungAufZahlungseingang() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        TestForderung auftrag= new TestForderung(TestForderung.Art.FORDERUNG,10,Geld.createAmount(20),repository);
        TestZahlungsEingang zahlungseingang= new TestZahlungsEingang(TestZahlungsEingang.Art.GUTSCHRIFT,20,Geld.createAmount(18),repository);

        assertEquals(2,repository.getBuchungen().size());
        
        assertEquals(Geld.createAmount(20),auftrag.getSoll());
        
        BuchungsAuftrag buchung = repository.getBuchungen().get(0);
        assertEquals(Geld.createAmount(20),buchung.getWerte().get(new TestKonto(1, "Rest")));
       
        assertEquals(Geld.createAmount(18),zahlungseingang.getOffen());
        assertEquals(Geld.createAmount(0),zahlungseingang.getZugewiesen());
        
        auftrag.bezahlen(repository,zahlungseingang,Geld.createAmount(5));
        
        assertEquals(3,repository.getBuchungen().size());
        
        assertEquals(Geld.createAmount(15),auftrag.getRest());
        assertEquals(Geld.createAmount(5),auftrag.getHaben());
        
        assertEquals(Geld.createAmount(13),zahlungseingang.getOffen());
        assertEquals(Geld.createAmount(5),zahlungseingang.getZugewiesen());
        
        auftrag.bezahlen(repository,zahlungseingang,Geld.createAmount(1));
        
        assertEquals(Geld.createAmount(14),auftrag.getRest());
        assertEquals(Geld.createAmount(6),auftrag.getHaben());
        
        assertEquals(Geld.createAmount(12),zahlungseingang.getOffen());
        assertEquals(Geld.createAmount(6),zahlungseingang.getZugewiesen());
        
    }
}
