package repositories;

import javax.money.MonetaryAmount;

import beans.Geld;
import gebühren.FixeGebührRepository;
import gebühren.ProzentualRepository;

public class TestProzentualRepository extends TestRepository implements ProzentualRepository, FixeGebührRepository{
    private double prozentsatz;
    private MonetaryAmount betrag;
    private MonetaryAmount fixeGebühr;

    public TestProzentualRepository() {
        super();
    }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }

    @Override
    public double getGebührenProzentsatz() {
        return prozentsatz;
    }

    @Override
    public MonetaryAmount getFixeGebühr() {
        return fixeGebühr;
    }

    public void setProzentsatz(double prozentsatz) {
        this.prozentsatz = prozentsatz;
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
