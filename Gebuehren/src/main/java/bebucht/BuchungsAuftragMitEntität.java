package bebucht;

import java.util.ArrayList;
import java.util.List;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntität extends BuchungsAuftrag {
    private List<BebuchteEntität> entitäten;

    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText, Werte werte,BebuchteEntität entität) {
        super(art, buchungsText, werte);
        this.entitäten = new ArrayList<>();
        addEntität(entität);
    }

    public List<BebuchteEntität> getEntitäten() {
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
