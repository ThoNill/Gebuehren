package abrechnung;

import beans.Bewegungen;

public interface BewegungenQuelle {
     Bewegungen getWerte(Abrechnung abrechnung);
     Enum<?> getArt();
     String getBuchungsText();
     
     default Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
         return abrechnung;
     }
     
}
