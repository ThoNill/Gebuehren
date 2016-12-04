package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import abrechnung.BewegungenQuelle;
import buchung.Bewegungen;
import buchung.Konto;

public class Überzahlung implements BewegungenQuelle{
    private Konto überzahlungsKonto;
    private Enum<?> überzahlungsArt;
    private Abrechnung abrechnung;
    
    public Überzahlung(Enum überzahlungsArt,Konto überzahlungsKonto,Abrechnung abrechnung) {
        this.überzahlungsKonto = überzahlungsKonto;
        this.überzahlungsArt = überzahlungsArt;
        this.abrechnung = abrechnung;
    }
    
    @Override
    public Abrechnung getRelevanteAbrechnung() {
        return this.abrechnung;
    }

    @Override
    public Bewegungen getBewegungen() {
        MonetaryAmount alteÜberzahlung = getAlteÜbezahlung();
        MonetaryAmount aktuellesSaldo = abrechnung.getSaldo();
        MonetaryAmount reduziertesSaldo = aktuellesSaldo.subtract(alteÜberzahlung);
        Bewegungen w = new Bewegungen();
        if (reduziertesSaldo.isNegative()) {
            w.put(überzahlungsKonto, reduziertesSaldo.negate());
        }
        return w;
    }

    protected MonetaryAmount getAlteÜbezahlung() {
        Bewegungen alteBuchung = abrechnung.getAktuelleWerte(überzahlungsArt);
        return alteBuchung.get(überzahlungsKonto);
    }

    @Override
    public Enum<?> getArt() {
        return überzahlungsArt;
    }

    @Override
    public String getBuchungsText() {
        return "Überzahlungen";
    }
    
  

}
