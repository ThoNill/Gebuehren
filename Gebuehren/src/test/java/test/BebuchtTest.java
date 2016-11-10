package test;

import org.junit.Test;

import static org.junit.Assert.*;
import bebucht.BuchungsAuftragMitEntit‰t;
import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;
import betrag.Geld;
import buchung.BuchungsAuftrag;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        BebuchtTestEntit‰t auftrag= new BebuchtTestEntit‰t(BebuchtTestEntit‰t.Art.AUFTRAG,10,Geld.createAmount(20),repository);

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
}
