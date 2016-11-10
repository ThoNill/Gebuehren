package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import buchung.AutoMwstKonto;
import buchung.Konto;

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
