package gebuehren;

import beans.Abrechnung;

public interface Gebuehr<G> {

	
	Werte getGebuehrWerte(Abrechnung a);
	
}
