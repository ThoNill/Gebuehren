package abrechnung;

import beans.Bewegungen;

public interface WerteQuelle {
     Bewegungen getWerte(Abrechnung abrechnung);
     Enum<?> getArt();
     String getBuchungsText();
     
     default Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
         return abrechnung;
     }
     
}
