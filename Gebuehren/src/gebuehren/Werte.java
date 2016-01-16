package gebuehren;

import java.util.HashMap;

import beans.Geld;
import beans.Konto;

public class Werte<G> extends HashMap<Konto, Geld<G>> {
	
	public Werte<G> differenz(Werte<G> b) {
		Werte<G> dWerte = new Werte<>();
		for(Konto k : this.keySet()) {
			Geld<G> d = differenz(k,b);
			if (d != null) {
				dWerte.put(k,d);
			}
		}
		for(Konto k : b.keySet()) {
			if(!dWerte.containsKey(k)) {
				Geld<G> d = b.get(k);
				dWerte.put(k,d.invert());
			}
			
		}
		return dWerte;
	}
	
	private Geld<G> differenz(Konto k, Werte<G> b) {
		Geld<G> ga = get(k);
		Geld<G> gb = b.get(k);

		if (ga != null) {
			if (gb != null) {
				return ga.subtract(gb);
			}
			return ga;
		}
		if (gb != null) {
			return gb.invert();
		}
		return null;
	}
	
	public Geld<G> summe(Geld<G> g) {
		Geld<G> summe = g.getNull();
		for(Konto k : this.keySet()) {
			Geld<G> v =  get(k);
			if (v != null) {
				summe = summe.add(v);
			}
		}
		return summe;
	}

}
