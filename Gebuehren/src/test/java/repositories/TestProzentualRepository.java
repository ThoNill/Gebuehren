package repositories;

import javax.money.MonetaryAmount;

import beans.Geld;
import geb�hren.FixeGeb�hrRepository;
import geb�hren.ProzentualRepository;

public class TestProzentualRepository extends TestRepository implements ProzentualRepository, FixeGeb�hrRepository{
    private double prozentsatz;
    private MonetaryAmount betrag;
    private MonetaryAmount fixeGeb�hr;

    public TestProzentualRepository() {
        super();
    }

    @Override
    public MonetaryAmount getBetrag() {
        return betrag;
    }

    @Override
    public double getGeb�hrenProzentsatz() {
        return prozentsatz;
    }

    @Override
    public MonetaryAmount getFixeGeb�hr() {
        return fixeGeb�hr;
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

    public void setFixeGeb�hr(long cent) {
        this.fixeGeb�hr = Geld.createAmount(cent/100.0);
    }
    
    public void setFixeGeb�hr(MonetaryAmount betrag) {
        this.fixeGeb�hr = betrag;
    }

 
}
