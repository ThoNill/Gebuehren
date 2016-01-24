package beans.impl;

import beans.Konto;

public class SimpleKonto implements Konto {
	int nummer;
	String name;

	public SimpleKonto(int i, String name) {
		super();
		this.nummer = i;
		this.name = name;
	}

	public int getNummer() {
		return nummer;
	}
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nummer;
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
		SimpleKonto other = (SimpleKonto) obj;
		if (nummer != other.nummer)
			return false;
		return true;
	}



}
