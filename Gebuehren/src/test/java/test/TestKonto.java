package test;

import javax.money.MonetaryAmount;

import buchung.Bewegungen;
import buchung.Konto;

public class TestKonto implements Konto {
    private int nummer;
    private String name;

    public TestKonto(int nummer, String name) {
        super();
        this.nummer = nummer;
        this.name = name;
    }

    @Override
    public int getNummer() {
        return nummer;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Bewegungen ergänzen(MonetaryAmount amount) {
        Bewegungen w = new Bewegungen();
        w.put(this,amount);
        return w;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        TestKonto other = (TestKonto) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nummer != other.nummer)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TestKonto [nummer=" + nummer + ", name=" + name + "]";
    }

    @Override
    public boolean hasErgänzung() {
        return false;
    }

 

}
