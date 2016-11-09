package geb�hren;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.BewegungenQuelle;
import beans.Bewegungen;

public abstract class Geb�hr<REPO extends Repository> implements BewegungenQuelle {
    private REPO repository;
    private Enum<?> art;
    private String buchungsText;
    
    public Geb�hr(REPO repository,Enum<?> art,String buchungsText) {
        super();
        this.repository = repository;
        this.art = art;
        this.buchungsText = buchungsText;
    }
    
    @Override
    public Bewegungen getWerte(Abrechnung abrechnung) {
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
