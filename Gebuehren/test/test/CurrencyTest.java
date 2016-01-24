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

import org.javamoney.moneta.Money;
import org.junit.Ignore;
import org.junit.Test;

public class CurrencyTest {

	@Test
	public void test() {
		MonetaryAmountFactory<?> factory = Monetary
				.getAmountFactory(MonetaryAmountFactoryQueryBuilder.of().setFixedScale(true)
						.setPrecision(10).setMaxScale(2).build());
		System.out.println(factory.getAmountType().getName());
	}

	@Ignore
	@Test
	public void test1() {
		MonetaryAmountFactory<?> fast = Monetary
				.getAmountFactory(org.javamoney.moneta.FastMoney.class);

		Monetary.getDefaultRounding();

		CurrencyUnit euro = Monetary.getCurrency("EUR");

		MonetaryAmount a1 = Money.of(123.45, euro);
		System.out.println(a1.getContext().getProviderName());

		MonetaryRounding r = Monetary.getRounding(euro);

		System.out.println(euro.getDefaultFractionDigits());

		Collection<MonetaryAmountFactory<?>> fs = Monetary.getAmountFactories();
		for (MonetaryAmountFactory<?> mf : fs) {
			System.out.println(mf.getAmountType().getName());
			MonetaryAmount a = r.apply(mf.setCurrency("EUR")
					.setNumber(10334.315).create());

			System.out.println(a.toString());
			System.out.println(a.getNumber().getScale());

			MonetaryAmountFormat germanFormat = MonetaryFormats
					.getAmountFormat(Locale.GERMANY);
			System.out.println(germanFormat.format(a));
		}
	}

	@Ignore
	@Test
	public void test12() {
		CurrencyUnit euro = Monetary.getCurrency("CHF");

		MonetaryAmount a1 = Money.of(123.45, euro);
		System.out.println(a1.getContext().getProviderName());

		MonetaryRounding r = Monetary.getRounding(euro);

		System.out.println(euro.getDefaultFractionDigits());

		Collection<MonetaryAmountFactory<?>> fs = Monetary.getAmountFactories();
		for (MonetaryAmountFactory<?> mf : fs) {
			System.out.println(mf.getAmountType().getName());
			MonetaryAmount a = r.apply(mf.setCurrency("CHF")
					.setNumber(10334.315).create());

			System.out.println(a.toString());
			System.out.println(a.getNumber().getScale());

			MonetaryAmountFormat germanFormat = MonetaryFormats
					.getAmountFormat(Locale.GERMANY);
			System.out.println(germanFormat.format(a));
		}
	}

}
