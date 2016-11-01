package überzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Repository;

public interface ÜberzahlugsRepository extends Repository{

    double getÜberzahlungsZins();

    MonetaryAmount getUntergrenzeFürZinsberechnung();

    MonetaryAmount getMinimalerÜberzahlungsZins();

}