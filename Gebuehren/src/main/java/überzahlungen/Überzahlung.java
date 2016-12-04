package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
import buchung.Bewegungen;
import buchung.Konto;

public class �berzahlung implements BewegungenQuelle{
    private Konto �berzahlungsKonto;
    private Enum<?> �berzahlungsArt;
    private Abrechnung abrechnung;
    
    public �berzahlung(Enum �berzahlungsArt,Konto �berzahlungsKonto,Abrechnung abrechnung) {
        this.�berzahlungsKonto = �berzahlungsKonto;
        this.�berzahlungsArt = �berzahlungsArt;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return this.abrechnung;
    }

    @Override
    public Bewegungen getBewegungen() {
        MonetaryAmount alte�berzahlung = getAlte�bezahlung();
        MonetaryAmount aktuellesSaldo = abrechnung.getSaldo();
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alte�berzahlung);
        Bewegungen w = new Bewegungen();
        if (reduziertesSaldo.isNegative()) {
            w.put(�berzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlte�bezahlung() {
        Bewegungen alteBuchung = abrechnung.getAktuelleWerte(�berzahlungsArt);
        return alteBuchung.get(�berzahlungsKonto);
    }

    @Override
    public Enum<?> getArt() {
        return �berzahlungsArt;
    }

    @Override
    public String getBuchungsText() {
        return "�berzahlungen";
    }
    
  

}
