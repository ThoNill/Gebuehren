package repositories;

import javax.money.MonetaryAmount;

import geb�hren.FixeGeb�hrRepository;

public class TestFixeGeb�rRepository extends TestRepository implements FixeGeb�hrRepository {
    private MonetaryAmount geb�hr;
    
    public TestFixeGeb�rRepository() {
   }

    public MonetaryAmount getFixeGeb�hr() {
        return geb�hr;
    }

    public void setGeb�hr(MonetaryAmount geb�hr) {
        this.geb�hr = geb�hr;
    }

}
