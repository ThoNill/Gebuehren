package test;

import org.junit.Test;

import static org.junit.Assert.*;
import beans.Geld;
import bebucht.BuchungsAuftragMitEntit‰t;
import bebucht.BuchungsRepository;
import bebucht.Entit‰tMit‹berg‰ngen;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        BebuchtTestEntit‰t auftrag= new BebuchtTestEntit‰t(BebuchtTestEntit‰t.Art.AUFTRAG,10,Geld.createAmount(20),repository);

        assertEquals(1,repository.getBuchungen().size());
        
        assertEquals(Geld.createAmount(20),auftrag.getSoll());
        
        BuchungsAuftragMitEntit‰t buchung = repository.getBuchungen().get(0);
        assertEquals(Geld.createAmount(20),buchung.getWerte().get(new TestKonto(20, "Soll")));
       
    }
}
