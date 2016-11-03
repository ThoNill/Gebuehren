package test;

import java.util.Collection;

import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryAmountFactoryQueryBuilder;
import javax.money.MonetaryRounding;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import org.apache.log4j.Logger;
import org.javamoney.moneta.Money;
import org.junit.Ignore;
import org.junit.Test;


public class CurrencyTest {
    public static final Logger LOG = Logger.getLogger(CurrencyTest.class
            .getSimpleName());

    @Test
    public void test() {
        MonetaryAmountFactory<?> factory = Monetary
                .getAmountFactory(MonetaryAmountFactoryQueryBuilder.of()
                        .setFixedScale(true).setPrecision(10).setMaxScale(2)
                        .build());
        LOG.info(factory.getAmountType().getName());
    }

    @Ignore
    @Test
    public void testEUR() {

        testCurrency("EUR");
    }

    @Ignore
    @Test
    public void testCHF() {

        testCurrency("CHF");
    }

    public void testCurrency(String currency) {
        CurrencyUnit euro = Monetary.getCurrency(currency);

        MonetaryAmount a1 = Money.of(123.45, euro);
        LOG.info(a1.getContext().getProviderName());

        MonetaryRounding r = Monetary.getRounding(euro);

        LOG.info(euro.getDefaultFractionDigits());

        Collection<MonetaryAmountFactory<?>> fs = Monetary.getAmountFactories();
        for (MonetaryAmountFactory<?> mf : fs) {
            LOG.info(mf.getAmountType().getName());
            MonetaryAmount a = r.apply(mf.setCurrency(currency)
                    .setNumber(10334.315).create());

            LOG.info(a.toString());
            LOG.info(a.getNumber().getScale());

            MonetaryAmountFormat germanFormat = MonetaryFormats
                    .getAmountFormat(Locale.GERMANY);
            LOG.info(germanFormat.format(a));
        }
    }

}
