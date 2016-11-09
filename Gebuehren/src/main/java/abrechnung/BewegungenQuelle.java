package abrechnung;

import beans.Bewegungen;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine BewegungenQuelle erzeugt mehrere Bewegungen, die dann in einem Buchungsauftrag gepackt werden k�nnen
 * Z.Bsp Geb�hren oder �berzahlungen
 *
 */
public interface BewegungenQuelle {
     Bewegungen getBewegungen(Abrechnung abrechnung);
     Enum<?> getArt();
     String getBuchungsText();
     
     default Abrechnung getRelevanteAbrechnung(Abrechnung abrechnung) {
         return abrechnung;
     }
     
}
