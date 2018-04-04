package Elements;

import java.io.Serializable;

public class Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Czas czas;

	public Czas getCzas() {
		return czas;
	}
	
	public double getCzasTrwania() {
		return czas.getCzas();
	}

	
	public void setCzasTrwania(Czas czas) {
		this.czas = czas;
	}
	
	public Element(Czas czas)
	{
		setCzasTrwania(czas);
	}
}
