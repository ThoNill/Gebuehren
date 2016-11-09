package abrechnung;

import java.util.ArrayList;
import java.util.List;

import beans.BuchungsAuftrag;
import beans.Bewegungen;

public abstract class Abrechnung {
    private List<WerteQuelle> quellen = new ArrayList<>();
    private Repository repository;

    public Abrechnung(Repository db) {
        super();
        this.repository = db;
    }
    
    public abstract Abrechnung nächsteAbrechnung();
    
    public void abrechnen() {
        for (WerteQuelle g : quellen) {
            abrechnen(g);
        }
    }

    private void abrechnen(WerteQuelle g) {
        Bewegungen neu = g.getWerte(this);
        Abrechnung relevanteAbrechnung = g.getRelevanteAbrechnung(this);
        Bewegungen alt = repository.getAktuelleWerte(g.getArt(),relevanteAbrechnung);
        Bewegungen diff = neu.differenz(alt);
        repository.insertBuchung(relevanteAbrechnung,new BuchungsAuftrag(g.getArt(),g.getBuchungsText(),diff));
    }
    
    public boolean add(WerteQuelle e) {
        return quellen.add(e);
    }

    public Repository getRepository() {
        return repository;
    }
}
