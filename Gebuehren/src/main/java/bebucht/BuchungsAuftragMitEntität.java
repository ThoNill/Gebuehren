package bebucht;

import beans.BuchungsAuftrag;
import beans.Werte;

public class BuchungsAuftragMitEntität extends BuchungsAuftrag {
    private BebuchteEntität entität;

    public BuchungsAuftragMitEntität(Enum<?> art, String buchungsText, Werte werte,BebuchteEntität entität) {
        super(art, buchungsText, werte);
        this.entität = entität;
    }

    public BebuchteEntität getEntität() {
        return entität;
    }

}
