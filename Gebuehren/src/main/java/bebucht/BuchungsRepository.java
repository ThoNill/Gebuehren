package bebucht;

import buchung.Konto;

public interface BuchungsRepository {
    Konto getSachKonto(BebuchteEntität entität);
    Konto getGegenKonto(BebuchteEntität entität);
    void insertBuchung(BuchungsAuftragMitEntität auftrag);
}
