package test;

import org.junit.Test;

import static org.junit.Assert.*;
import beans.Geld;
import bebucht.BuchungsRepository;
import bebucht.EinfacheEntität;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        EinfacheEntität auftrag = new BebuchtTestEntität(BebuchtTestEntität.Art.AUFTRAG,10,Geld.createAmount(20),repository);
        assertEquals(1,repository.getBuchungen().size());
       
    }
}
