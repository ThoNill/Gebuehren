package beans.impl;

import beans.Geld;



public class LongGeld implements beans.Geld<LongGeld> {
	public static final LongGeld NULL = new LongGeld(0L);
	
	private long betrag;

	public LongGeld(long betrag) {
		super();
		this.betrag = betrag;
	}


	@Override
	public boolean isNull() {
		return betrag == 0;
	}

	@Override
	public String toString() {
		return "LongGeld [betrag=" + betrag + "]";
	}
	
	@Override
	public LongGeld mult(float factor) {
		// TODO noch keine Rundung
		return new LongGeld((long)(factor * betrag));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (betrag ^ (betrag >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongGeld other = (LongGeld) obj;
		if (betrag != other.betrag)
			return false;
		return true;
	}


	@Override
	public LongGeld getNull() {
		return new LongGeld(0);
	}


	@Override
	public LongGeld invert() {
		return new LongGeld(-betrag);
	}


	@Override
	public Geld<LongGeld> subtract(Geld<LongGeld> b) {
		return new LongGeld(betrag - ((LongGeld)b).betrag);
	}


	@Override
	public Geld<LongGeld> add(Geld<LongGeld> b) {
		return new LongGeld(betrag + ((LongGeld)b).betrag);
	}

}
