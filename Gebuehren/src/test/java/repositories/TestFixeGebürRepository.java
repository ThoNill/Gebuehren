package repositories;

import geb�hren.FixeGeb�hrRepository;

import javax.money.MonetaryAmount;

public class TestFixeGeb�rRepository extends TestRepository implements FixeGeb�hrRepository {
    private MonetaryAmount geb�hr;
    
    public TestFixeGeb�rRepository() {
   }

    @Override
    public MonetaryAmount getFixeGeb�hr() {
        return geb�hr;
    }

    public void setGeb�hr(MonetaryAmount geb�hr) {
        this.geb�hr = geb�hr;
    }

}
