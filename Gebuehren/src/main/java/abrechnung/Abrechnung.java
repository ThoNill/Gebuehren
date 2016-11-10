package abrechnung;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine Abrechnung ist eine Liste von BewegungenQuelle, die beim Abrechnen eine Reihe von Buchungen erzeugen.
 * Jede BewegungenQuelle erzeugt eine Buchung. Diese wird mit eventuell schon vorhandenen Buchungen desselben Typs
 * verglichen. Dann wird die Differenz zwischen Soll und Istzustand gebucht.
 *
 */
public abstract class Abrechnung {
    private List<BewegungenQuelle> quellen = new ArrayList<>();
    private Repository repository;

    public Abrechnung(Repository db) {
        super();
        this.repository = db;
    }
    
    public abstract Abrechnung nächsteAbrechnung();
    
    public void abrechnen() {
        for (BewegungenQuelle g : quellen) {
            abrechnen(g);
        }
    }

    private void abrechnen(BewegungenQuelle g) {
        Bewegungen neu = g.getBewegungen();
        Bewegungen alt = getAktuelleWerte(g.getArt());
        Bewegungen diff = neu.differenz(alt);
        repository.insertBuchung(g.getRelevanteAbrechnung(),new BuchungsAuftrag(g.getArt(),g.getBuchungsText(),diff));
    }
    
    public boolean add(BewegungenQuelle e) {
        return quellen.add(e);
    }

    public Repository getRepository() {
        return repository;
    }
    
    public abstract MonetaryAmount getSaldo();
    public abstract double getMwstSatz();
    public abstract Konto getMwstKonto();
    public abstract Bewegungen getAktuelleWerte(Enum<?> art);
}
