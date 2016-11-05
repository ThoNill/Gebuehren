package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.WerteQuelle;
import beans.Geld;
import beans.Konto;
import beans.Werte;

public class �berzahlungsZins implements WerteQuelle{
    private �berzahlugsRepository repository;
    private Konto �berzahlungsZinsKonto;
    private Konto �berzahlungsKonto;
    private Enum<?> �berzahlungsArt;
    private Enum<?> art;
    
    
    public �berzahlungsZins(�berzahlugsRepository repository,Konto �berzahlungsKonto,Konto �berzahlungsZinsKonto,Enum �berzahlungsArt,Enum art) {
        this.repository = repository;
        this.�berzahlungsZinsKonto = �berzahlungsZinsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.art = art;
    }

    @Override
    public Werte getWerte(Abrechnung abrechnung) {
        Werte �berzahlung = repository.getAktuelleWerte(�berzahlungsArt, abrechnung);
        Werte w = new Werte();
        MonetaryAmount �berzahlungsBetrag = �berzahlung.get(�berzahlungsKonto);
        if (Geld.absolutGr��er(�berzahlungsBetrag,repository.getUntergrenzeF�rZinsberechnung())) {
            MonetaryAmount �berzahlungsZins = Geld.percentAmount(�berzahlungsBetrag,repository.get�berzahlungsZins());
            
            if (Geld.absolutGr��er(�berzahlungsZins,repository.getMinimaler�berzahlungsZins())) {
                w.put(�berzahlungsZinsKonto, �berzahlungsZins.negate());
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
        return "�berzahlungszins";
    }

}
