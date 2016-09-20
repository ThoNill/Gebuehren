package gebuehren;

import beans.Abrechnung;

@FunctionalInterface
public interface Gebuehr {

    Werte getGebuehrWerte(Abrechnung a);

}
