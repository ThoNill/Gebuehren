package bebucht;

import java.util.HashSet;

import buchung.Konto;

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
        for (Übergang w : this) {
            if (art.equals(w.getArt())) {
                return w;
            }
        }
        throw new IllegalArgumentException("Kein Übergang gefunden zu "+art.name() + " gefunden");
    }

}
