package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.Repository;
import abrechnung.WerteQuelle;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public class �berzahlungZins implements WerteQuelle{
    private �berzahlugsRepository repository;
    private Konto �berzahlungsZinsKonto;
    private int �berzahlungsArt;
    
    public �berzahlungZins(�berzahlugsRepository repository,Konto �berzahlungsZinsKonto,int �berzahlungsArt) {
        this.repository = repository;
        this.�berzahlungsZinsKonto = �berzahlungsZinsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        Werte �berzahlung = repository.getAktuelleWerte(�berzahlungsArt, abrechnung);
        Werte w = new Werte();
        MonetaryAmount �berzahlungsBetrag = �berzahlung.get(�berzahlungsZinsKonto);
        if (Geld.absolutGr��er(�berzahlungsBetrag,repository.getUntergrenzeF�rZinsberechnung())) {
            MonetaryAmount �berzahlungsZins = Geld.percentAmount(�berzahlungsBetrag,repository.get�berzahlungsZins());
            
            if (Geld.absolutGr��er(�berzahlungsZins,repository.getMinimaler�berzahlungsZins())) {
                w.put(�berzahlungsZinsKonto, �berzahlungsZins);
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
        return "�berzahlungszins";
    }

}
