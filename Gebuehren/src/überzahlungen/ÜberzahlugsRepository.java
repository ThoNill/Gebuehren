package �berzahlungen;

import javax.money.MonetaryAmount;

import abrechnung.Repository;

public interface �berzahlugsRepository extends Repository{

    double get�berzahlungsZins();

    MonetaryAmount getUntergrenzeF�rZinsberechnung();

    MonetaryAmount getMinimaler�berzahlungsZins();

}