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
	public void test1() {
		Konto k1 = new SimpleKonto(1001,"Gebuehr");
		Konto k2 = new SimpleKonto(1002,"Mwst");
		Konto k3 = new SimpleKonto(1003,"Pauschale");
		
		Werte<Long> werte = new Werte<>();
		werte.put(k1,new LongGeld(3));
		werte.put(k2,new LongGeld(4));
		werte.put(k3,new LongGeld(5));
		
		Geld<Long> summe = werte.summe(new LongGeld(0L));
		
		assertEquals(summe.value().longValue(),3L+4L+5L);
		
	}
	
	@Test
	public void test2() {
		Konto k1 = new SimpleKonto(1001,"Gebuehr");
		Konto k2 = new SimpleKonto(1002,"Mwst");
		Konto k3 = new SimpleKonto(1003,"Pauschale");
		
		Werte<Long> werte1 = new Werte<>();
		werte1.put(k1,new LongGeld(3));
		werte1.put(k2,new LongGeld(4));
		werte1.put(k3,new LongGeld(5));
		
		Werte<Long> werte2 = new Werte<>();
		werte2.put(k1,new LongGeld(2));
		werte2.put(k2,new LongGeld(3));
		werte2.put(k3,new LongGeld(4));
		
		Werte<Long> werte3 = werte1.differenz(werte2);
		
		for(Konto k : werte3.keySet()) {
			Geld<Long> v =  werte3.get(k);
			if (v != null) {
				assertEquals(v.value().longValue(),1L);
			}
		}
		
	}	

}
