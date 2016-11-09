package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
import beans.Geld;
import beans.Konto;
import beans.Bewegungen;

public class ÜberzahlungsZins implements BewegungenQuelle{
    private ÜberzahlugsRepository repository;
    private Konto ÜberzahlungsZinsKonto;
    private Konto ÜberzahlungsKonto;
    private Enum<?> überzahlungsArt;
    private Enum<?> art;
    
    
    public ÜberzahlungsZins(ÜberzahlugsRepository repository,Konto ÜberzahlungsKonto,Konto ÜberzahlungsZinsKonto,Enum überzahlungsArt,Enum art) {
        this.repository = repository;
        this.ÜberzahlungsZinsKonto = ÜberzahlungsZinsKonto;
        this.überzahlungsArt = überzahlungsArt;
        this.ÜberzahlungsKonto = ÜberzahlungsKonto;
        this.art = art;
    }

    @Override
    public Bewegungen getBewegungen(Abrechnung abrechnung) {
        Bewegungen überzahlung = repository.getAktuelleWerte(überzahlungsArt, abrechnung);
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
