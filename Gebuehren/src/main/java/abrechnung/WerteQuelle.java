package abrechnung;

import beans.Werte;

public interface WerteQuelle {
     Werte getWerte(Abrechnung abrechnung);
     Enum<?> getArt();
     String getBuchungsText();
     
     default Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
         return abrechnung;
     }
     
}
