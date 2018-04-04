package Elements;

/**
 * @author Nexon
 *	Class holding the values of pitch fore notes
 */
public enum Wysokosc {
	c1(60), d1(62), e1(64), f1(65), g1(67), a1(69), h1(71), c2(72), 
	d2(74), e2(76), f2(77);
	private int value;

	private Wysokosc(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}
}

