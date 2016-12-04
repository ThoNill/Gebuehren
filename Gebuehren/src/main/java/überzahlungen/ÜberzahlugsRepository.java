package überzahlungen;

import javax.money.MonetaryAmount;

public interface ÜberzahlugsRepository {

    double getÜberzahlungsZins();

    MonetaryAmount getUntergrenzeFürZinsberechnung();

    MonetaryAmount getMinimalerÜberzahlungsZins();

}