package gebühren;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
/**
 * 
 * @author Thomas Nill
 * 
 * Eine Gebühr dient dazu, Bewegungen für eine Gebührenbuchung zu erzeugen.
 * Jede Gebührart hat ihr Repository das zu ihr passt und die zur Berechnung der Gebühr benötigten Werte holt.
 *
 */
import abrechnung.Repository;
import buchung.Bewegungen;

public abstract class Gebühr<REPO extends Repository> implements BewegungenQuelle {
    private REPO repository;
    private Enum<?> art;
    private String buchungsText;
    
    public Gebühr(REPO repository,Enum<?> art,String buchungsText) {
        super();
        this.repository = repository;
        this.art = art;
        this.buchungsText = buchungsText;
    }
    
    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        markieren(repository,abrechnung);
        return getWerte(repository,abrechnung);
    }
    
    void markieren(REPO a,Abrechnung abrechnung) {};
    protected abstract Bewegungen getWerte(REPO repository,Abrechnung abrechnung);

    public REPO getRepository() {
        return repository;
    }

    @Override
    public Enum<?> getArt() {
        return art;
    }

    @Override
    public String getBuchungsText() {
        return buchungsText;
    }
    
}
