package bebucht;

import abrechnung.Abrechnung;
import beans.BuchungsAuftrag;
import beans.Konto;

public interface BuchungsRepository {
    Konto getSachKonto(BebuchteEntität entität);
    Konto getGegenKonto(BebuchteEntität entität);
    void insertBuchung(BuchungsAuftragMitEntität auftrag);
}
