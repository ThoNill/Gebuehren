package gebühren;

import abrechnung.BewegungenQuelle;
import buchung.Bewegungen;
/**
 * 
 * @author Thomas Nill
 * 
 * Eine Gebühr dient dazu, Bewegungen für eine Gebührenbuchung zu erzeugen.
 * Jede Gebührart hat ihr Repository das zu ihr passt und die zur Berechnung der Gebühr benötigten Werte holt.
 *
 */

public abstract class Gebühr<REPO extends MarkierendesRepository> implements
        BewegungenQuelle {
    private REPO repository;
    private Enum<?> art;
    private String buchungsText;

    public Gebühr(REPO repository, Enum<?> art, String buchungsText) {
        super();
        this.repository = repository;
        this.art = art;
        this.buchungsText = buchungsText;
    }

    @Override
    public Bewegungen getBewegungen() {
        repository.markieren(getRelevanteAbrechnung());
        return getWerte(repository);
    }

  

    protected abstract Bewegungen getWerte(REPO repository);

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
