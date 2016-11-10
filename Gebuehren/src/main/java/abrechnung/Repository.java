package abrechnung;

import buchung.BuchungsAuftrag;

public interface Repository {
    public void insertBuchung(Abrechnung abrechnung, BuchungsAuftrag auftrag);
}
