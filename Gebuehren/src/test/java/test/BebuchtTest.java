package test;

import org.junit.Test;

import static org.junit.Assert.*;
import beans.Geld;
import bebucht.BuchungsRepository;
import bebucht.EinfacheEntit�t;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        EinfacheEntit�t auftrag = new BebuchtTestEntit�t(BebuchtTestEntit�t.Art.AUFTRAG,10,Geld.createAmount(20),repository);
        assertEquals(1,repository.getBuchungen().size());
       
    }
}
