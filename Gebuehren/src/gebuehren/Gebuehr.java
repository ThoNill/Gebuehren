package gebuehren;

import beans.Abrechnung;

public interface Gebuehr {
    void markieren(Abrechnung a);
    Werte getGebuehrWerte(Abrechnung a);
}
