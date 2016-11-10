package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public class �berzahlungsZins implements BewegungenQuelle{
    private �berzahlugsRepository repository;
    private Konto �berzahlungsZinsKonto;
    private Konto �berzahlungsKonto;
    private Enum<?> �berzahlungsArt;
    private Enum<?> art;
    private Abrechnung abrechnung;
    
    
    public �berzahlungsZins(�berzahlugsRepository repository,Konto �berzahlungsKonto,Konto �berzahlungsZinsKonto,Enum �berzahlungsArt,Enum art,Abrechnung abrechnung) {
        this.repository = repository;
        this.�berzahlungsZinsKonto = �berzahlungsZinsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.art = art;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return abrechnung;
    }

    @Override
    public Bewegungen getBewegungen() {
        Bewegungen �berzahlung = abrechnung.getAktuelleWerte(�berzahlungsArt);
        Bewegungen w = new Bewegungen();
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
