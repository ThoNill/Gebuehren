package abrechnung;

import beans.Werte;

public interface WerteQuelle {
     Werte getWerte(Abrechnung abrechnung);
     int getArt();
     String getBuchungsText();
     
}
