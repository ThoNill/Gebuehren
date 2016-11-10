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
 * Ein {@link BuchungsAuftrag} der eine Liste von {@link BebuchteEntit�t} h�lt.
 *
 */
public class BuchungsAuftragMitEntit�t extends BuchungsAuftrag {
    private Set<BebuchteEntit�t> entit�ten;
 
    
    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText) {
        this(art, buchungsText, new Bewegungen());
    }
    
    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText, Bewegungen bewegungen) {
        super(art, buchungsText, bewegungen);
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
