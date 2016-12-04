package repositories;

import abrechnung.Abrechnung;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;

public interface AktuelleWerteRepository {
    Bewegungen getAktuelleWerte(Enum<?> art,Abrechnung abrechnung);
    void insertBuchung(BuchungsAuftrag auftrag);
}
