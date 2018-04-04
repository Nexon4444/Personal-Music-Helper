package Elements;
import Elements.Element;
public class Pauza extends Element{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pauza(Czas czas) {
		super(czas);
	}

	public Pauza(Pauza pauza) {
		super (pauza.getCzas());
	}

}
