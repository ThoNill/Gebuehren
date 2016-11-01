package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.WerteQuelle;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public class ÜberzahlungZins implements WerteQuelle{
    private ÜberzahlugsRepository repository;
    private Konto ÜberzahlungsZinsKonto;
    private int überzahlungsArt;
    
    public ÜberzahlungZins(ÜberzahlugsRepository repository,Konto ÜberzahlungsZinsKonto,int überzahlungsArt) {
        this.repository = repository;
        this.ÜberzahlungsZinsKonto = ÜberzahlungsZinsKonto;
        this.überzahlungsArt = überzahlungsArt;
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
    public int getArt() {
        return 0;
    }

    @Override
    public String getBuchungsText() {
        return "Überzahlungszins";
    }

}
