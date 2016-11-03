package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.WerteQuelle;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public class ÜberzahlungZins implements WerteQuelle{
    private ÜberzahlugsRepository repository;
    private Konto ÜberzahlungsZinsKonto;
    private Enum<?> überzahlungsArt;
    private Enum<?> art;
    
    
    public ÜberzahlungZins(ÜberzahlugsRepository repository,Konto ÜberzahlungsZinsKonto,Enum überzahlungsArt,Enum art) {
        this.repository = repository;
        this.ÜberzahlungsZinsKonto = ÜberzahlungsZinsKonto;
        this.überzahlungsArt = überzahlungsArt;
        this.art = art;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        Werte überzahlung = repository.getAktuelleWerte(überzahlungsArt, abrechnung);
        Werte w = new Werte();
        MonetaryAmount überzahlungsBetrag = überzahlung.get(ÜberzahlungsZinsKonto);
        if (Geld.absolutGrößer(überzahlungsBetrag,repository.getUntergrenzeFürZinsberechnung())) {
            MonetaryAmount überzahlungsZins = Geld.percentAmount(überzahlungsBetrag,repository.getÜberzahlungsZins());
            
            if (Geld.absolutGrößer(überzahlungsZins,repository.getMinimalerÜberzahlungsZins())) {
                w.put(ÜberzahlungsZinsKonto, überzahlungsZins);
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
