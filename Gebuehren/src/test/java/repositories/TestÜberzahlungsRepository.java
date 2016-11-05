package repositories;

import javax.money.MonetaryAmount;

import beans.Geld;
import �berzahlungen.�berzahlugsRepository;


public class Test�berzahlungsRepository extends TestRepository implements �berzahlugsRepository{
    
    public enum Art {
        NEUE_�BERZAHLUNG,
        �BERZAHLUNG_AUS_DEM_VORMONAT,
        �BERZAHLUNGS_ZINS
    }
    private double �berzahlungsZins;
    private MonetaryAmount UntergrenzeF�rZinsberechnung = Geld.getNull();
    private MonetaryAmount Minimaler�berzahlungsZins = Geld.getNull();
    
    
    
    @Override
    public double get�berzahlungsZins() {
        return �berzahlungsZins;
    }
    @Override
    public MonetaryAmount getUntergrenzeF�rZinsberechnung() {
        return UntergrenzeF�rZinsberechnung;
    }
    @Override
    public MonetaryAmount getMinimaler�berzahlungsZins() {
        return  Minimaler�berzahlungsZins;
    }
    
    public void set�berzahlungsZins(double �berzahlungsZins) {
        �berzahlungsZins = �berzahlungsZins;
    }
    
    public void setUntergrenzeF�rZinsberechnung(
            MonetaryAmount untergrenzeF�rZinsberechnung) {
        UntergrenzeF�rZinsberechnung = untergrenzeF�rZinsberechnung;
    }
    
    public void setMinimaler�berzahlungsZins(
            MonetaryAmount minimaler�berzahlungsZins) {
        Minimaler�berzahlungsZins = minimaler�berzahlungsZins;
    }
    
 
  

}
