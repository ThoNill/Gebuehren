package gebuehren;

import beans.Abrechnung;
import beans.Geld;

public interface Repository<G> {
	Werte<G> getAktuelleWerte(Abrechnung a);
	
	Geld<G> saldo(Abrechnung a);
	
	public void insertBuchung(Abrechnung a,Gebuehr g,Werte<G> werte);
	

	
}
