package repositories;

import javax.money.MonetaryAmount;

import beans.Geld;
import überzahlungen.ÜberzahlugsRepository;


public class TestÜberzahlungsRepository extends TestRepository implements ÜberzahlugsRepository{
    
    public enum Art {
        NEUE_ÜBERZAHLUNG,
        ÜBERZAHLUNG_AUS_DEM_VORMONAT,
        ÜBERZAHLUNGS_ZINS
    }
    private double ÜberzahlungsZins;
    private MonetaryAmount UntergrenzeFürZinsberechnung = Geld.getNull();
    private MonetaryAmount MinimalerÜberzahlungsZins = Geld.getNull();
    
    
    
    @Override
    public double getÜberzahlungsZins() {
        return ÜberzahlungsZins;
    }
    @Override
    public MonetaryAmount getUntergrenzeFürZinsberechnung() {
        return UntergrenzeFürZinsberechnung;
    }
    @Override
    public MonetaryAmount getMinimalerÜberzahlungsZins() {
        return  MinimalerÜberzahlungsZins;
    }
    
    public void setÜberzahlungsZins(double überzahlungsZins) {
        ÜberzahlungsZins = überzahlungsZins;
    }
    
    public void setUntergrenzeFürZinsberechnung(
            MonetaryAmount untergrenzeFürZinsberechnung) {
        UntergrenzeFürZinsberechnung = untergrenzeFürZinsberechnung;
    }
    
    public void setMinimalerÜberzahlungsZins(
            MonetaryAmount minimalerÜberzahlungsZins) {
        MinimalerÜberzahlungsZins = minimalerÜberzahlungsZins;
    }
    
 
  

}
