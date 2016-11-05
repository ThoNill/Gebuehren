package repositories;

import javax.money.MonetaryAmount;

import gebühren.FixeGebührRepository;

public class TestFixeGebürRepository extends TestRepository implements FixeGebührRepository {
    private MonetaryAmount gebühr;
    
    public TestFixeGebürRepository() {
   }

    public MonetaryAmount getFixeGebühr() {
        return gebühr;
    }

    public void setGebühr(MonetaryAmount gebühr) {
        this.gebühr = gebühr;
    }

}
