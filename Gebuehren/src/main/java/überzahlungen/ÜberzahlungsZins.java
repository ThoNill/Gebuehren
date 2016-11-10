package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public class ÜberzahlungsZins implements BewegungenQuelle{
    private ÜberzahlugsRepository repository;
    private Konto ÜberzahlungsZinsKonto;
    private Konto ÜberzahlungsKonto;
    private Enum<?> überzahlungsArt;
    private Enum<?> art;
    private Abrechnung abrechnung;
    
    
    public ÜberzahlungsZins(ÜberzahlugsRepository repository,Konto ÜberzahlungsKonto,Konto ÜberzahlungsZinsKonto,Enum überzahlungsArt,Enum art,Abrechnung abrechnung) {
        this.repository = repository;
        this.ÜberzahlungsZinsKonto = ÜberzahlungsZinsKonto;
        this.überzahlungsArt = überzahlungsArt;
        this.ÜberzahlungsKonto = ÜberzahlungsKonto;
        this.art = art;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return abrechnung;
    }

    @Override
    public Bewegungen getBewegungen() {
        Bewegungen überzahlung = abrechnung.getAktuelleWerte(überzahlungsArt);
        Bewegungen w = new Bewegungen();
        MonetaryAmount überzahlungsBetrag = überzahlung.get(ÜberzahlungsKonto);
        if (Geld.absolutGrößer(überzahlungsBetrag,repository.getUntergrenzeFürZinsberechnung())) {
            MonetaryAmount überzahlungsZins = Geld.percentAmount(überzahlungsBetrag,repository.getÜberzahlungsZins());
            
            if (Geld.absolutGrößer(überzahlungsZins,repository.getMinimalerÜberzahlungsZins())) {
                w.put(ÜberzahlungsZinsKonto, überzahlungsZins.negate());
            }
        };
        return w;
    }

    @Override
    public Enum<?> getArt() {
        return art;
    }

    @Override
    public String getBuchungsText() {
        return "Überzahlungszins";
    }

}
