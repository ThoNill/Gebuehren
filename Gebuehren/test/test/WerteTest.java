package test;

import static org.junit.Assert.*;

import javax.money.MonetaryAmount;

import org.junit.Test;

import static beans.Geld.*;
import beans.Konto;
import beans.Werte;
import beans.impl.SimpleKonto;

public class WerteTest {

	@Test
	public void test1() {
		Konto k1 = new SimpleKonto(1001,"Gebuehr");
		Konto k2 = new SimpleKonto(1002,"Mwst");
		Konto k3 = new SimpleKonto(1003,"Pauschale");
		
		Werte werte = new Werte();
		werte.put(k1,createAmount(3));
		werte.put(k2,createAmount(4));
		werte.put(k3,createAmount(5));
		
		MonetaryAmount summe = werte.summe(createAmount(0L));
		
		assertEquals(3L+4L+5L,summe.getNumber().longValue());
		
	}
	
	@Test
	public void test2() {
		Konto k1 = new SimpleKonto(1001,"Gebuehr");
		Konto k2 = new SimpleKonto(1002,"Mwst");
		Konto k3 = new SimpleKonto(1003,"Pauschale");
		
		Werte werte1 = new Werte();
		werte1.put(k1,createAmount(3));
		werte1.put(k2,createAmount(4));
		werte1.put(k3,createAmount(5));
		
		Werte werte2 = new Werte();
		werte2.put(k1,createAmount(2));
		werte2.put(k2,createAmount(3));
		werte2.put(k3,createAmount(4));
		
		Werte werte3 = werte1.differenz(werte2);
		
		for(Konto k : werte3.keySet()) {
			MonetaryAmount v =  werte3.get(k);
			if (v != null) {
				assertEquals(v.getNumber().longValue(),1L);
			}
		}
		
	}	
	
	@Test
	public void runden() {
		for(long l=0;l<100;l++) {
			rundenTesten(l);
		}
		runden(1.46,1.456);
	}
	
	
	public void rundenTesten(long l) {
		runden(((double)l)/100,Double.parseDouble("0." + ((l<10) ? "0":"") + l));
	}

	protected void runden(double expected, double value) {
		assertEquals(expected,createAmount(value).getNumber().doubleValue(),0.001);
	}


}
