package repositories;

import geb�hren.ProzentualRepository;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import test.TestKonto;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;
import abrechnung.Abrechnung;
import abrechnung.Repository;

public class TestRepositoryMitBuchungsAuftr�gen implements ProzentualRepository{
    List<BuchungsAuftrag> auftr�ge = new ArrayList<>();
    private Konto mwst = new TestKonto(1,"Mwst");
    private MonetaryAmount betrag;

    public TestRepositoryMitBuchungsAuftr�gen(double betrag) {
        this.betrag = Geld.createAmount(betrag); 
    }

    @Override
    public Bewegungen getAktuelleWerte(Enum<?> art, Abrechnung a) {
        Bewegungen bewegungen = new Bewegungen();
        for(BuchungsAuftrag b : auftr�ge) {
            if (art.equals(b.getArt())) {
                bewegungen = bewegungen.add(b.getWerte());
            }
        }
        return bewegungen;
    }
    
   
    public MonetaryAmount getAktuelleWerte(Enum<?> art, Konto konto) {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : auftr�ge) {
            if (art.equals(b.getArt())) {
                summe = summe.add(b.getWerte().get(konto));
            }
        }
        return summe;
    }
    
    public MonetaryAmount getAktuelleWerte(Konto konto) {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : auftr�ge) {
           summe = summe.add(b.getWerte().get(konto));
        }
        return summe;
    }

    @Override
    public MonetaryAmount saldo(Abrechnung a) {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : auftr�ge) {
            summe = summe.add(b.getWerte().summe());
         
        }
        return summe;
    }

    @Override
    public void insertBuchung(Abrechnung abrechnung, BuchungsAuftrag auftrag) {
       auftr�ge.add(auftrag);
        
    }

    @Override
    public double getMwstSatz(Abrechnung abrechnung) {
        return 0.19;
    }

    @Override
    public Konto getMwstKonto(Abrechnung abrechnung) {
       return mwst;
    }

    @Override
    public MonetaryAmount getBetrag() {
       return betrag;
    }

    @Override
    public double getGeb�hrenProzentsatz() {
        return 0.03;
    }

}
