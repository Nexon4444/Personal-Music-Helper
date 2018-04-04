package Elements;

public enum Czas {
CalaNuta(1),
PolNuta(0.5),
CwiercNuta(0.25),
OsemkaNuta(0.125),
CalaPauza(1),
PolPauza(0.5),
CwiercPauza(0.25),
OsemkaPauza(0.125);

private double value;

private Czas(double value)
{
	this.value = value;
}

public double getCzas()
{
	return value;
}

}
