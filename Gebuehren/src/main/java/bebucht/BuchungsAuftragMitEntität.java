package bebucht;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntit�t extends BuchungsAuftrag {
    private Set<BebuchteEntit�t> entit�ten;
 
    
    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText) {
        super(art, buchungsText, new Werte());
        this.entit�ten = new HashSet<>();
    }
    
    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText, Werte werte) {
        super(art, buchungsText, werte);
        this.entit�ten = new HashSet<>();
    }

    public Set<BebuchteEntit�t> getEntit�ten() {
        return entit�ten;
    }
    
    public List<BebuchteEntit�t> getEntit�ten(Enum<?> art) {
        List<BebuchteEntit�t> ergebnis = new  ArrayList<>();
        for(BebuchteEntit�t e : entit�ten)  {
            if(art.equals(e.getArt())){
                ergebnis.add(e);
            }
        }
        return ergebnis;
    }
    
    public BuchungsAuftragMitEntit�t addEntit�t(BebuchteEntit�t entit�t) {
        entit�ten.add(entit�t);
        return this;
    }

 

}
