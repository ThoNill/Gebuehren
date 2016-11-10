package entities;

import javax.money.MonetaryAmount;

import repositories.AktuelleWerteRepository;
import test.TestKonto;
import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.Konto;

public class TestAbrechnung extends Abrechnung {
    private int nummer;
    private MonetaryAmount saldo = Geld.getNull();
    private Konto mwst = new TestKonto(1, "Mwst");
    private double mwstSatz = 0.19;
    private AktuelleWerteRepository repository;
  

    public TestAbrechnung(int nummer, AktuelleWerteRepository repository) {
        super(repository);
        this.nummer = nummer;
        this.repository = repository;
    }
    
    @Override
    public Bewegungen getAktuelleWerte(Enum art) {
        return repository.getAktuelleWerte(art,this);
    }
    
    @Override
    public Abrechnung nächsteAbrechnung() {
        return new TestAbrechnung(nummer + 1, getRepository());
    }
    
    @Override
    public AktuelleWerteRepository getRepository() {
        return repository;
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

    @Override
    public MonetaryAmount getSaldo() {
        return saldo;
    }

    public void setSaldo(MonetaryAmount saldo) {
        this.saldo = saldo;
    }

    public void setSaldo(long cent) {
        this.saldo = Geld.createAmount(cent / 100.0);
    }

    @Override
    public Konto getMwstKonto() {
        return mwst;
    }

    @Override
    public double getMwstSatz() {
        return mwstSatz;
    }

}
