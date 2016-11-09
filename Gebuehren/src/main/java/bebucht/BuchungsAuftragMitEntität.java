package bebucht;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntität extends BuchungsAuftrag {
    private Set<BebuchteEntität> entitäten;
 
    
    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText) {
        super(art, buchungsText, new Werte());
        this.entitäten = new HashSet<>();
    }
    
    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText, Werte werte) {
        super(art, buchungsText, werte);
        this.entitäten = new HashSet<>();
    }

    public Set<BebuchteEntität> getEntitäten() {
        return entitäten;
    }
    
    public List<BebuchteEntität> getEntitäten(Enum<?> art) {
        List<BebuchteEntität> ergebnis = new  ArrayList<>();
        for(BebuchteEntität e : entitäten)  {
            if(art.equals(e.getArt())){
                ergebnis.add(e);
            }
        }
        return ergebnis;
    }
    
    public BuchungsAuftragMitEntität addEntität(BebuchteEntität entität) {
        entitäten.add(entität);
        return this;
    }

 

}
