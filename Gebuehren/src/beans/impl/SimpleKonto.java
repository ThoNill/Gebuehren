package beans.impl;

import beans.Konto;

public class SimpleKonto implements Konto {
	String nummer;
	String name;

	public SimpleKonto(String nummer, String name) {
		super();
		this.nummer = nummer;
		this.name = name;
	}

	public String getNummer() {
		return nummer;
	}
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nummer == null) ? 0 : nummer.hashCode());
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
		if (nummer == null) {
			if (other.nummer != null)
				return false;
		} else if (!nummer.equals(other.nummer))
			return false;
		return true;
	}

}
