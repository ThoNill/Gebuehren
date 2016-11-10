package repositories;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import buchung.Bewegungen;

public interface AktuelleWerteRepository extends Repository {
    Bewegungen getAktuelleWerte(Enum<?> art,Abrechnung abrechnung);
}
