package gebuehren;

import beans.Abrechnung;

public interface Gebuehr<G> {

	
	Werte<G> getGebuehrWerte(Abrechnung a);
	
}
