package test;

import static org.junit.Assert.*;
import org.junit.Test;
import beans.Konto;
import beans.impl.AutoMwstKonto;

public class AutoMwstKontoTest {

    public AutoMwstKontoTest() {
      
    }
    
    @Test
    public void NameUndNummer() {
        Konto k = new TestKonto(2,"Betrag");
        Konto m = new TestKonto(3,"Mwst");
        
        AutoMwstKonto amk= new AutoMwstKonto(k,m,19);
        assertEquals(amk.getNummer(),k.getNummer());
        assertEquals(amk.getName(),k.getName());
        
    }

}
