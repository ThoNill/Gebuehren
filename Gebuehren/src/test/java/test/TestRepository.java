package test;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.BuchungsAuftrag;
import beans.Geld;
import beans.Konto;
import beans.Werte;
import gebuehren.FixeGeb�hrRepository;
import gebuehren.ProzentualRepository;

public class TestRepository implements ProzentualRepository, FixeGeb�hrRepository{
    private Konto mwst = new TestKonto(1,"Mwst");
    private double prozentsatz;
    private MonetaryAmount saldo;
    private Werte aktuelleWerte;
    private MonetaryAmount betrag;
    private MonetaryAmount fixeGeb�hr;

    public TestRepository() {
        super();
    }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }

    @Override
    public Werte getAktuelleWerte(int art, Abrechnung a) {
        return aktuelleWerte;
    }

    @Override
    public MonetaryAmount saldo(Abrechnung a) {
       return saldo;
    }

    @Override
    public void insertBuchung(Abrechnung abrechnung, BuchungsAuftrag auftrag) {
    }

    @Override
    public double getMwstSatz(Abrechnung abrechnung) {
        return 0.19;
    }

    @Override
    public Konto getMwstKonto(Abrechnung abrechnung) {
        return mwst;
    }

    @Override
    public double getGeb�hrenProzentsatz() {
        return prozentsatz;
    }

    @Override
    public MonetaryAmount getGeb�hr() {
        return fixeGeb�hr;
    }

    public void setProzentsatz(double prozentsatz) {
        this.prozentsatz = prozentsatz;
    }

    public void setSaldo(long cent) {
        this.saldo = Geld.createAmount(cent/100.0);
    }
    
    public void setSaldo(MonetaryAmount betrag) {
        this.saldo = betrag;
    }

    public void setAktuelleWerte(Werte aktuelleWerte) {
        this.aktuelleWerte = aktuelleWerte;
    }

    public void setBetrag(long cent) {
        this.betrag = Geld.createAmount(cent/100.0);
    }
    
    public void setBetrag(MonetaryAmount betrag) {
        this.betrag = betrag;
    }

    public void setFixeGeb�hr(long cent) {
        this.fixeGeb�hr = Geld.createAmount(cent/100.0);
    }
    
    public void setFixeGeb�hr(MonetaryAmount betrag) {
        this.fixeGeb�hr = betrag;
    }

 
}
