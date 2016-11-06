package test;

import org.junit.Test;

import static org.junit.Assert.*;
import beans.Geld;
import bebucht.BuchungsRepository;

public class BebuchtTest {

    public BebuchtTest() {
       
    }

    @Test
    public void test() {
        BebuchungsTestRepository repository = new BebuchungsTestRepository();
        BebuchtTestEntität auftrag = new BebuchtTestEntität(BebuchtTestEntität.Art.AUFTRAG,10,Geld.createAmount(20),repository);
        assertEquals(1,repository.getBuchungen().size());
       
    }
}
