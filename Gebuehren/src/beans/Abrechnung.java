package beans;

import gebuehren.Gebuehr;
import gebuehren.Repository;
import gebuehren.Werte;

import java.util.ArrayList;
import java.util.List;

public class Abrechnung {
    List<Gebuehr> gebuehren = new ArrayList<>();
    Repository db;

    public void abrechnen() {
        for (Gebuehr g : gebuehren) {
            gebuehrAbrechnen(g);
        }

    }

    private void gebuehrAbrechnen(Gebuehr g) {
        Werte neu = g.getGebuehrWerte(this);
        Werte alt = db.getAktuelleWerte(this);
        Werte diff = neu.differenz(alt);
        db.insertBuchung(this, g, diff);
    }
}
