package geb�hren;

import abrechnung.BewegungenQuelle;
import buchung.Bewegungen;
/**
 * 
 * @author Thomas Nill
 * 
 * Eine Geb�hr dient dazu, Bewegungen f�r eine Geb�hrenbuchung zu erzeugen.
 * Jede Geb�hrart hat ihr Repository das zu ihr passt und die zur Berechnung der Geb�hr ben�tigten Werte holt.
 *
 */

public abstract class Geb�hr<REPO extends MarkierendesRepository> implements
        BewegungenQuelle {
    private REPO repository;
    private Enum<?> art;
    private String buchungsText;

    public Geb�hr(REPO repository, Enum<?> art, String buchungsText) {
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
