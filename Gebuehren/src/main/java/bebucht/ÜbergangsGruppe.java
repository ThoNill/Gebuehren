package bebucht;

import java.util.HashSet;

import buchung.Konto;

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
        for (�bergang w : this) {
            if (art.equals(w.getArt())) {
                return w;
            }
        }
        throw new IllegalArgumentException("Kein �bergang gefunden zu "+art.name() + " gefunden");
    }

}
