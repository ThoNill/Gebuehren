package bebucht;

import java.util.ArrayList;
import java.util.List;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntit�t extends BuchungsAuftrag {
    private List<BebuchteEntit�t> entit�ten;

    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText, Werte werte,BebuchteEntit�t entit�t) {
        super(art, buchungsText, werte);
        this.entit�ten = new ArrayList<>();
        addEntit�t(entit�t);
    }

    public List<BebuchteEntit�t> getEntit�ten() {
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
