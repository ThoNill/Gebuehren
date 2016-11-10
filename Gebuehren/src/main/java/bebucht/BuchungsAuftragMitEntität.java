package bebucht;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;

/**
 * 
 * @author Thomas Nill
 * 
 * Ein {@link BuchungsAuftrag} der eine Liste von {@link BebuchteEntität} hält.
 *
 */
public class BuchungsAuftragMitEntität extends BuchungsAuftrag {
    private Set<BebuchteEntität> entitäten;
 
    
    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText) {
        this(art, buchungsText, new Bewegungen());
    }
    
    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText, Bewegungen bewegungen) {
        super(art, buchungsText, bewegungen);
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
