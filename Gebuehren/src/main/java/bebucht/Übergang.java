package bebucht;

import beans.Konto;

public class Übergang {
    private Enum<?> art;
    private Enum<?> vonStatus;
    private Enum<?> nachStatus;
    private Konto vonKonto;
    private Konto nachKonto;
    

    public Übergang(Enum<?> art,Enum<?> vonStatus, Enum<?> nachStatus, Konto vonKonto,
            Konto nachKonto) {
        super();
        this.art = art;
        this.vonStatus = vonStatus;
        this.nachStatus = nachStatus;
        this.vonKonto = vonKonto;
        this.nachKonto = nachKonto;
     }

    
    public Enum<?> getArt() {
        return art;
    }

    public Enum<?> getVonStatus() {
        return vonStatus;
    }

    public Enum<?> getNachStatus() {
        return nachStatus;
    }

    public Konto getVonKonto() {
        return vonKonto;
    }

    public Konto getNachKonto() {
        return nachKonto;
    }

 


}
