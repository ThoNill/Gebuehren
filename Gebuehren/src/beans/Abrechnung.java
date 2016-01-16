package beans;

import gebuehren.Gebuehr;
import gebuehren.Repository;
import gebuehren.Werte;

import java.util.ArrayList;
import java.util.List;

public class Abrechnung<G> {
		List<Gebuehr<G>> gebuehren = new ArrayList<>();
		Repository<G> db;
	
		public void abrechnen() {
			for(Gebuehr<G> g : gebuehren) {
				gebuehrAbrechnen(g);
			}
			
		}

		private void gebuehrAbrechnen(Gebuehr<G> g) {
			Werte<G> neu = g.getGebuehrWerte(this);
			Werte<G> alt = db.getAktuelleWerte(this);
			Werte<G> diff  = neu.differenz(alt);
			db.insertBuchung(this, g, diff);
		}
}
