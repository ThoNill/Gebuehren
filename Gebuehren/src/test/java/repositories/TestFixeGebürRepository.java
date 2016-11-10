package repositories;

import gebühren.FixeGebührRepository;

import javax.money.MonetaryAmount;

public class TestFixeGebürRepository extends TestRepository implements FixeGebührRepository {
    private MonetaryAmount gebühr;
    
    public TestFixeGebürRepository() {
   }

    @Override
    public MonetaryAmount getFixeGebühr() {
        return gebühr;
    }

    public void setGebühr(MonetaryAmount gebühr) {
        this.gebühr = gebühr;
    }

}
