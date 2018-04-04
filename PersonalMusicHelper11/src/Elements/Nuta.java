package Elements;
import Elements.Element;
public class Nuta extends Element{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Nuta(Czas czas) {
		super(czas);
		// TODO Auto-generated constructor stub
	}
	
	public Nuta(Czas czas, Wysokosc wysokosc) {
		super(czas);
		this.wysokosc = wysokosc;
		// TODO Auto-generated constructor stub
	}
	
	public Nuta(Nuta nuta) {
		super(nuta.getCzas());
		this.wysokosc=nuta.wysokosc;
		
	}

	private Wysokosc wysokosc;

	public Wysokosc getWysokosc() {
		return wysokosc;
	}

	public void setWysokosc(Wysokosc wysokosc) {
		this.wysokosc = wysokosc;
	}
}
