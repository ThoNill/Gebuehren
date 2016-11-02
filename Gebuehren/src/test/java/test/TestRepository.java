package test;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import beans.BuchungsAuftrag;
import beans.Geld;
import beans.Konto;
import beans.Werte;
import gebuehren.FixeGebührRepository;
import gebuehren.ProzentualRepository;

public class TestRepository implements ProzentualRepository, FixeGebührRepository{
    private Konto mwst = new TestKonto(1,"Mwst");
    private double prozentsatz;
    private MonetaryAmount saldo;
    private Werte aktuelleWerte;
    private MonetaryAmount betrag;
    private MonetaryAmount fixeGebühr;

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
    public double getGebührenProzentsatz() {
        return prozentsatz;
    }

    @Override
    public MonetaryAmount getGebühr() {
        return fixeGebühr;
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

    public void setFixeGebühr(long cent) {
        this.fixeGebühr = Geld.createAmount(cent/100.0);
    }
    
    public void setFixeGebühr(MonetaryAmount betrag) {
        this.fixeGebühr = betrag;
    }

 
}
