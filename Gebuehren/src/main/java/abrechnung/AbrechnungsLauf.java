package abrechnung;

import java.util.ArrayList;
import java.util.List;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;

/**
 * 
 * @author Thomas Nill
 * 
 * Ein AbrechnungLauf ist eine Liste von BewegungenQuelle, die beim Abrechnen eine Reihe von Buchungen erzeugen.
 * Jede BewegungenQuelle erzeugt eine Buchung. Diese wird mit eventuell schon vorhandenen Buchungen desselben Typs
 * verglichen. Dann wird die Differenz zwischen Soll und Istzustand gebucht.
 *
 */
public class AbrechnungsLauf {
    private List<BewegungenQuelle> quellen = new ArrayList<>();
   

    public AbrechnungsLauf() {
        super();
    }
    
  
    
    public void abrechnen() {
        for (BewegungenQuelle g : quellen) {
            abrechnen(g);
        }
    }

    private void abrechnen(BewegungenQuelle g) {
        Abrechnung abrechnung = g.getRelevanteAbrechnung();
        Bewegungen neu = g.getBewegungen();
        Bewegungen alt = abrechnung.getAktuelleWerte(g.getArt());
        Bewegungen diff = neu.differenz(alt);
        abrechnung.insertBuchung(new BuchungsAuftrag(g.getArt(),g.getBuchungsText(),diff));
    }
    
    public boolean add(BewegungenQuelle e) {
        return quellen.add(e);
    }


  
}
