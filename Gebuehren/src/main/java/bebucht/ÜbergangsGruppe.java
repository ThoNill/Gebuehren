package bebucht;

import java.util.HashSet;

import buchung.Konto;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine Gruppe aus {@link Übergang} die in einerm bestimmten Status 
 * einer {@link BebuchtenEntität} möglichen Übergänge verwaltet 
 * 
 *
 */
public class ÜbergangsGruppe extends HashSet<Übergang> {

    public ÜbergangsGruppe() {
    }

    public ÜbergangsGruppe addÜbergang(Enum<?> art,
            Enum<?> vonStatus, Enum<?> nachStatus, Konto vonKonto,
            Konto nachKonto) {
        Übergang übergang = new Übergang(art, vonStatus, nachStatus,
                vonKonto, nachKonto);
        add(übergang);
        return this;
    }

    public Übergang getÜbergang(Enum<?> art) {
        if (art == null) {
            throw new IllegalArgumentException("Art darf nicht Null sein");
        }
        for (Übergang übergang : this) {
            if (art.equals(übergang.getArt())) {
                return übergang;
            }
        }
        throw new IllegalArgumentException("Kein Übergang gefunden zu "+art.name() + " gefunden");
    }

}
