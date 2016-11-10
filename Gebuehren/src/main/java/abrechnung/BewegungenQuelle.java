package abrechnung;

import buchung.Bewegungen;

/**
 * 
 * @author Thomas Nill
 * 
 * Eine BewegungenQuelle erzeugt mehrere Bewegungen, die dann in einem Buchungsauftrag gepackt werden k�nnen
 * Z.Bsp Geb�hren oder �berzahlungen
 *
 */
public interface BewegungenQuelle {
     Bewegungen getBewegungen();
     Enum<?> getArt();
     String getBuchungsText();
     Abrechnung getRelevanteAbrechnung();
  
     
}
