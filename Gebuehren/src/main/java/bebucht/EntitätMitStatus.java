package bebucht;


public abstract class Entit�tMitStatus implements BebuchteEntit�t{
    protected  Enum<?> status;
    
    public Entit�tMitStatus(Enum<?> status) {
        super();
        this.status = status;
    }

    @Override
    public Enum<?> getStatus() {
        return status;
    }

    protected void setStatus(Enum<?> status) {
        this.status = status;
    }
  
    
}
