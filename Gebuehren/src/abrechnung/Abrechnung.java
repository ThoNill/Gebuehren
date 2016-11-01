package abrechnung;

import java.util.ArrayList;
import java.util.List;

import beans.BuchungsAuftrag;
import beans.Werte;

public class Abrechnung {
    private List<WerteQuelle> quellen = new ArrayList<>();
    private Repository repository;

    public Abrechnung(Repository db) {
        super();
        this.repository = db;
    }

    public void abrechnen() {
        for (WerteQuelle g : quellen) {
            abrechnen(g);
        }
    }

    private void abrechnen(WerteQuelle g) {
        Werte neu = g.getWerte(this);
        Werte alt = repository.getAktuelleWerte(g.getArt(),this);
        Werte diff = neu.differenz(alt);
        repository.insertBuchung( this,new BuchungsAuftrag(g.getArt(),g.getBuchungsText(),diff));
    }
    
    public boolean add(WerteQuelle e) {
        return quellen.add(e);
    }
}
