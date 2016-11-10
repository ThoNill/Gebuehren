package bebucht;

import buchung.BuchungsAuftrag;
import buchung.Konto;
/**
 * 
 * @author Thomas Nill
 * 
 * Ein Repository, aus dem ein {@link Konto} geholt und in das ein {@link BuchungsAuftrag}
 * eingestellt werden kann.
 *
 */
public interface BuchungsRepository {
    Konto getGegenKonto(BebuchteEntit�t entit�t);
    void insertBuchung(BuchungsAuftrag auftrag);
}
