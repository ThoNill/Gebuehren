package beans;

import java.util.Locale;

import javax.money.CurrencyContextBuilder;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryAmountFactoryQueryBuilder;
import javax.money.MonetaryContextBuilder;
import javax.money.MonetaryRounding;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

public class Geld {

	private final static CurrencyUnit euro;
	private final static MonetaryAmountFactory<?> factory;
	private final static MonetaryRounding round;
	private final static MonetaryAmountFormat germanFormat;

	static {
		euro = Monetary.getCurrency("EUR");
		round = Monetary.getRounding(euro);
		factory = Monetary.getAmountFactory(MonetaryAmountFactoryQueryBuilder
				.of().setFixedScale(true).setPrecision(10).setMaxScale(2)
				.build());
		germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMANY);
	}

	public static String format(MonetaryAmount amount) {
		return germanFormat.format(amount);
	}

	public static MonetaryAmount round(MonetaryAmount amount) {
		return round.apply(amount);
	}

	public static MonetaryAmount createAmount(long l) {
		return factory.setCurrency(euro.getCurrencyCode()).setNumber(l)
				.create();
	}

	public static MonetaryAmount createAmount(Number l) {
		return factory.setCurrency(euro.getCurrencyCode()).setNumber(l)
				.create();
	}

	public static MonetaryAmount createAmount(double l) {
		return round.apply(factory.setCurrency(euro.getCurrencyCode())
				.setNumber(l).create());
	}

	public static MonetaryAmount percentAmount(long percent, double l) {
		return round.apply(factory.setCurrency(euro.getCurrencyCode())
				.setNumber((((double) percent) / 100) * l).create());
	}
}
