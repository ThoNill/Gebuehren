package entities;

import abrechnung.Abrechnung;
import abrechnung.Repository;

public class TestAbrechnung extends Abrechnung {
    private int nummer;
  

    public TestAbrechnung(int nummer,Repository db) {
        super(db);
        this.nummer = nummer;
    }

    @Override
    public Abrechnung nächsteAbrechnung() {
        return new TestAbrechnung(nummer+1,getRepository());
    }

    public int getNummer() {
        return nummer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nummer;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestAbrechnung other = (TestAbrechnung) obj;
        if (nummer != other.nummer)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TestAbrechnung [nummer=" + nummer + "]";
    }

}
