package beans;

import beans.impl.LongGeld;

public interface Geld<G> {
	
	Geld<G> subtract(Geld<G> b);

	Geld<G> add(Geld<G> b);

	Geld<G> mult(float factor);

	boolean isNull();

	Geld<G> getNull();

	Geld<G> invert();
	
	G value();
	
}
