package bebucht;

import abrechnung.Abrechnung;
import beans.BuchungsAuftrag;
import beans.Konto;

public interface BuchungsRepository {
    Konto getSachKonto(BebuchteEntit�t entit�t);
    Konto getGegenKonto(BebuchteEntit�t entit�t);
    void insertBuchung(BuchungsAuftragMitEntit�t auftrag);
}
