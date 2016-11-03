package abrechnung;

import beans.Werte;

public interface WerteQuelle {
     Werte getWerte(Abrechnung abrechnung);
     Enum getArt();
     String getBuchungsText();
     
}
