package bebucht;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntit�t extends BuchungsAuftrag {
    private BebuchteEntit�t entit�t;

    public BuchungsAuftragMitEntit�t(Enum<?> art, String buchungsText, Werte werte,BebuchteEntit�t entit�t) {
        super(art, buchungsText, werte);
        this.entit�t = entit�t;
    }

    public BebuchteEntit�t getEntit�t() {
        return entit�t;
    }

}
