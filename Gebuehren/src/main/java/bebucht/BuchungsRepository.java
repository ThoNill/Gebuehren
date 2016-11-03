package bebucht;

import beans.Konto;

public interface BuchungsRepository {
    Konto getSachKonto(BebuchteEntit�t entit�t);
    Konto getGegenKonto(BebuchteEntit�t entit�t);
    void insertBuchung(BuchungsAuftragMitEntit�t auftrag);
}
