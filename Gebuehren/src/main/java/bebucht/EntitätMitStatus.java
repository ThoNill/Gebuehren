package bebucht;


public abstract class EntitätMitStatus implements BebuchteEntität{
    protected  Enum<?> status;
    
    public EntitätMitStatus(Enum<?> status) {
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
