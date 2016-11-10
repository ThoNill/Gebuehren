package bebucht;

import java.util.HashSet;

import buchung.Konto;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine Gruppe aus {@link �bergang} die in einerm bestimmten Status 
 * einer {@link BebuchtenEntit�t} m�glichen �berg�nge verwaltet 
 * 
 *
 */
public class �bergangsGruppe extends HashSet<�bergang> {

    public �bergangsGruppe() {
    }

    public �bergangsGruppe add�bergang(Enum<?> art,
            Enum<?> vonStatus, Enum<?> nachStatus, Konto vonKonto,
            Konto nachKonto) {
        �bergang �bergang = new �bergang(art, vonStatus, nachStatus,
                vonKonto, nachKonto);
        add(�bergang);
        return this;
    }

    public �bergang get�bergang(Enum<?> art) {
        if (art == null) {
            throw new IllegalArgumentException("Art darf nicht Null sein");
        }
        for (�bergang �bergang : this) {
            if (art.equals(�bergang.getArt())) {
                return �bergang;
            }
        }
        throw new IllegalArgumentException("Kein �bergang gefunden zu "+art.name() + " gefunden");
    }

}
