package repositories;

import javax.money.MonetaryAmount;

import test.TestKonto;
import abrechnung.Abrechnung;
import abrechnung.Repository;
import beans.BuchungsartUndText;
import beans.Geld;
import beans.Konto;
import beans.Werte;
import gebühren.FixeGebührRepository;
import gebühren.ProzentualRepository;

public class TestRepository implements Repository{

    private Konto mwst = new TestKonto(1,"Mwst");
    private MonetaryAmount saldo;
    private Werte aktuelleWerte;

    public TestRepository() {
        super();
        aktuelleWerte = new Werte();
    }

    @Override
    public Werte getAktuelleWerte(Enum art, Abrechnung a) {
        return aktuelleWerte;
    }

    @Override
    public MonetaryAmount saldo(Abrechnung a) {
       return saldo;
    }

    @Override
    public void insertBuchung(Abrechnung abrechnung, BuchungsartUndText auftrag) {
    }

    @Override
    public double getMwstSatz(Abrechnung abrechnung) {
        return 0.19;
    }

    @Override
    public Konto getMwstKonto(Abrechnung abrechnung) {
        return mwst;
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

}