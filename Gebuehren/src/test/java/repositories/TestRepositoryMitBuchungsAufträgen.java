package repositories;

import gebühren.ProzentualRepository;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import abrechnung.Abrechnung;
import betrag.Geld;
import buchung.Bewegungen;
import buchung.BuchungsAuftrag;
import buchung.Konto;

public class TestRepositoryMitBuchungsAufträgen implements ProzentualRepository, AktuelleWerteRepository{
    List<BuchungsAuftrag> aufträge = new ArrayList<>();
    private MonetaryAmount betrag;

    public TestRepositoryMitBuchungsAufträgen(double betrag) {
        this.betrag = Geld.createAmount(betrag); 
    }

   
    @Override
    public Bewegungen getAktuelleWerte(Enum<?> art,Abrechnung abrechnung) {
        Bewegungen bewegungen = new Bewegungen();
        for(BuchungsAuftrag b : aufträge) {
            if (art.equals(b.getArt())) {
                bewegungen = bewegungen.add(b.getWerte());
            }
        }
        return bewegungen;
    }
    
   
    public MonetaryAmount getAktuelleWerte(Enum<?> art, Konto konto) {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : aufträge) {
            if (art.equals(b.getArt())) {
                summe = summe.add(b.getWerte().get(konto));
            }
        }
        return summe;
    }
    
    public MonetaryAmount getAktuelleWerte(Konto konto) {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : aufträge) {
           summe = summe.add(b.getWerte().get(konto));
        }
        return summe;
    }

  
    public MonetaryAmount saldo() {
        MonetaryAmount summe = Geld.getNull();
        for(BuchungsAuftrag b : aufträge) {
            summe = summe.add(b.getWerte().summe());
         
        }
        return summe;
    }

    @Override
    public void insertBuchung(BuchungsAuftrag auftrag) {
       aufträge.add(auftrag);
        
    }

    @Override
    public MonetaryAmount getBetrag() {
       return betrag;
    }

    @Override
    public double getGebührenProzentsatz() {
        return 0.03;
    }


    @Override
    public void markieren(Abrechnung abrechnung) {
    }


   

}
