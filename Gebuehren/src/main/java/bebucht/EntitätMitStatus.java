package bebucht;



/**
 * 
 * @author Thomas Nill
 * 
 * Eine {@link BebuchteEntit�t} mit konkreter ID, art, Status.
 *
 */
public abstract class Entit�tMitStatus implements BebuchteEntit�t{
    private Enum<?> art;
    private long referenzId;
    private  Enum<?> status;
    
    public Entit�tMitStatus(Enum<?> art, long referenzId, Enum<?> status) {
        super();
        this.art = art;
        this.referenzId = referenzId;
        this.status = status;
    }

    @Override
    public long getId() {
        return referenzId;
    }

    @Override
    public Enum<?> getArt() {
        return art;
    }

    @Override
    public Enum<?> getStatus() {
        return status;
    }

    protected void setStatus(Enum<?> status) {
        this.status = status;
    }
  
    
}
