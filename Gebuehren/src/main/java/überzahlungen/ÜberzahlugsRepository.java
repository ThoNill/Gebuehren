package �berzahlungen;

import javax.money.MonetaryAmount;

public interface �berzahlugsRepository {

    double get�berzahlungsZins();

    MonetaryAmount getUntergrenzeF�rZinsberechnung();

    MonetaryAmount getMinimaler�berzahlungsZins();

}