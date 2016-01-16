package test;

import static org.junit.Assert.*;
import gebuehren.Werte;

import org.junit.Test;

import beans.Geld;
import beans.Konto;
import beans.impl.LongGeld;
import beans.impl.SimpleKonto;

public class WerteTest {

	@Test
	public void test() {
		Werte<Long> werte = new Werte<>();
		Konto k1 = new SimpleKonto(1001,"Gebuehr");
		Konto k2 = new SimpleKonto(1002,"Mwst");
		Konto k3 = new SimpleKonto(1003,"Pauschale");
		
		werte.put(k1,new LongGeld(3));
		werte.put(k2,new LongGeld(4));
		werte.put(k3,new LongGeld(5));
		
		Geld<Long> summe = werte.summe(new LongGeld(0L));
		
		assertEquals(summe.value().longValue(),3L+4L+5L);
		
	}

}
