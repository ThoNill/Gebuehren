package repositories;

import geb�hren.ProzentualRepository;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

public class TestRepositoryMitBuchungsAuftr�gen implements ProzentualRepository, AktuelleWerteRepository{
    List<BuchungsAuftrag> auftr�ge = new ArrayList<>();
    private MonetaryAmount betrag;

    public TestRepositoryMitBuchungsAuftr�gen(double betrag) {
        this.betrag = Geld.createAmount(betrag); 
    }

   
    @Override
    public Bewegungen getAktuelleWerte(Enum<?> art,Abrechnung abrechnung) {
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

  
    public MonetaryAmount saldo() {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : auftr�ge) {
            summe = summe.add(b.getWerte().summe());
         
        }
        return summe;
    }

    @Override
    public void insertBuchung(BuchungsAuftrag auftrag) {
       auftr�ge.add(auftrag);
        
    }

    @Override
    public MonetaryAmount getBetrag() {
       return betrag;
    }

    @Override
    public double getGeb�hrenProzentsatz() {
        return 0.03;
    }


    @Override
    public void markieren(Abrechnung abrechnung) {
    }


   

}
