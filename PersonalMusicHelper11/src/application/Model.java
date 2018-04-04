package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Elements.Element;
import Elements.Nuta;
import Elements.Pauza;
import Elements.ToggleButtonExtended;
import Elements.Wysokosc;
import Exceptions.ExceptionNiePelnyTakt;
import Exceptions.ExceptionNieWybranoElementu;
import Exceptions.ExceptionZaDuzaWartoscElementu;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import jm.constants.Pitches;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;


/**
 * @author Nexon
 * Model contains all the information about the scores and buttons
 * 
 */
public class Model {
	private double highLine = 45;
	private double lowLine = 105;
	private int spacesBetweenLines = 4*2;
	private int numberOfNotes = 11;
	protected double jumpVal; /**the distance between notes*/

	private double lineHeights[];
	private double czasDoWykorzystania = 1.0;
	protected ArrayList <ArrayList <Element>> utwor = new ArrayList <ArrayList <Element>>();
	protected ArrayList <Element> takt = new ArrayList <Element>();
	protected ToggleButtonExtended[] toggleButtonExtendeds;
	protected ToggleGroup group = new ToggleGroup();
	private Score score = new Score();
	private View view;
/**
 * 
 * @param toggleButtonExtendeds setter for toggleButtonExtendeds, array of toggle buttons to be created
 */
	public void setToggleButtonExtendeds(ToggleButtonExtended[] toggleButtonExtendeds) {
		this.toggleButtonExtendeds = toggleButtonExtendeds;
	}
	
/**
 * 
 * @return the number of bars in a score
 */
	public Integer getBarsEntered ()
	{
		return utwor.size();
	}
	
/**
 * Constructor for Model, sets the jumpVal (distance) between the notes in the staff, fills the lineHeigths table, and other constants
 * @param view is the current view class to which the model should refer to
 */
	public Model(View view)
	{
		makeJumpVal();
		makeLineHeights();
		this.view = view;
		view.setYmin(lowLine+2*jumpVal);
		setToggleButtonExtendeds(view.loadButtons(view.getProjektController().getIdHBox()));
		view.setToggleButtonExtendedsImages(this);
		
		score.setTempo(30);
	}
	
/**
 * 
 * @return jumval (distance) between the notes in the staff
 */
	
  	public double getJumpVal() {
		return jumpVal;
	}

/**
 * sets the constant jumpVal
 */
	public void makeJumpVal()
	{
		jumpVal = (lowLine - highLine)/spacesBetweenLines;
	}

	/**
	 *  fills the lineHeigths table, which defines the heights of the staff lines in the staff
	 */
	public void makeLineHeights()
	{
		lineHeights = new double[numberOfNotes+1];
		double level = lowLine+2*jumpVal;
		for (int i=0; i<numberOfNotes+1; i++)
		{
			lineHeights[i] = level;
			level=level-jumpVal;
		}
		
	}
	
	/**
	 * 
	 * @return boolean value, is the  note being entered the first in the bar
	 */
	
	public boolean isFirstNote()
	{
		return (takt.size() == 0);
	}
	
	/**
	 * Reacts to the event of clicking on the staff to decides if the element should be added, it not then
	 * throws an exception. Using the information about the event decides which line is the closest to the click-point
	 * if the element can be added, method add for array takt is called and the element is inserted into the bar, method 
	 * for viewing thenew element is called.
	 * @param event a click of a mouse event
	 */
	
	public void addElement(MouseEvent event) {
		ToggleButtonExtended temp = (ToggleButtonExtended)group.getSelectedToggle();

		try {
		if (temp == null) throw new ExceptionNieWybranoElementu("Zaznacz Guzik");
		if (czasDoWykorzystania-temp.getElement().getCzasTrwania() < 0)
			throw new ExceptionZaDuzaWartoscElementu("Za Duza Wartosc Elementu");
		
		czasDoWykorzystania=czasDoWykorzystania-temp.getElement().getCzasTrwania();
		if (temp.getElement().getClass() == Nuta.class)
		{	
			int i=0;
			double wys = event.getY();
			if (wys>=(lowLine+jumpVal)) i=0;
			else if (wys<=highLine) i = numberOfNotes-1;
			else for (; i<numberOfNotes; i++)
				if (lineHeights[i] >= wys && wys > lineHeights[i+1]) break;
			
			Nuta nuta = new Nuta((Nuta)temp.getElement());
			nuta.setWysokosc(Wysokosc.values()[i]);
			view.addNuta(this, nuta);
			takt.add(nuta);
			
		}
		else
		{
			Pauza pauza = new Pauza((Pauza)temp.getElement());
			view.addPauza(this, pauza);
			takt.add(new Pauza (temp.getElement().getCzas()));
		}
	} 
	catch (ExceptionZaDuzaWartoscElementu e) {
		view.printError("The element which you want to add is to big");
		System.err.println("Za Duza Wartosc Elementu");} 
	catch (ExceptionNieWybranoElementu e)
		{
		view.printError("Choose the element you want to add");
		System.err.println("Zaznacz Guzik");
		}
	catch (Exception e) {
			e.printStackTrace();
	}}

/**
 * Adds the bar to a score(a takt into utwor). If the bar is not fully filled an exception is threw.
 * Then takt is cleared, thus place for new elements is created.
 */
	public void enterBar() {
		try {
			if (czasDoWykorzystania != 0) throw new ExceptionNiePelnyTakt("Nie pelny takt");
			utwor.add(takt);
			czasDoWykorzystania = 1.0;
			System.out.println("Dodano takt do utworu!");
			view.printError("Bar added to score!");
			view.deleteElementsOfEnteredBar();
			takt = new ArrayList <Element>();

		} catch (ExceptionNiePelnyTakt e) {
			view.printError("Finish the Bar");
			System.err.println("Nie pelny takt");
		} catch (Exception e) {
			e.printStackTrace();
	}
	}

/**
 * Plays the music stored in utwor. Calls methods translate and doMusic.
 */
	public void play() {
		translate();
		doMusic();
		view.printError("The score is played");
	}

/**
 * Plays music
 */
	private void doMusic() {
		Play.midi(score);
	}

/**
 * translates the utwor array of takts to the jMusic library score
 */
	private void translate() 
	{
		
		score.clean();
		Part part = new Part();
		for (ArrayList <Element> taktAnalizowany : utwor)
		{
			Phrase phrase = new Phrase();
			for (Element elementAnalizowany : taktAnalizowany)
				{
					
					if (elementAnalizowany.getClass() == Nuta.class)
					{
						Nuta nuta = (Nuta)elementAnalizowany;
						nuta.getWysokosc();
						Note note = new Note();
						note.setDuration(nuta.getCzasTrwania()*4);
						note.setPitch(nuta.getWysokosc().getValue());
						phrase.add(note);
					}
					
					else 
					{
						Pauza pauza = (Pauza)elementAnalizowany;
						Note note = new Note(Pitches.REST, pauza.getCzasTrwania());
						phrase.add(note);
					}
					
				}
			part.add(phrase);
		}
		
		score.add(part);
	}
/**
 * resets the utwor array, jMusic score
 */
	public void reset() {
		score.removeAllParts();
		score.clean();
		utwor.clear();
		System.out.println("Usuniêto utwór");
		view.printError("Score deleted");
		
	}

/**
 * clears the takt array, so the bar can be filled again
 */
	public void clear() {
		czasDoWykorzystania=1.0;
		takt.clear();
		
	}
/**
 * Saves the utwor array on disk
 * @param nazwa the name of disk
 * @param idEnteredBarsNumber the id of the text field for showing the number of bars in the utwor array
 * @param model reference to the model
 */
	public void save(String nazwa, Text idEnteredBarsNumber, Model model) {

		// populate array

		// writing array to disk
		FileOutputStream f_out;
		try {
			f_out = new FileOutputStream(nazwa);
			@SuppressWarnings("resource")
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			obj_out.writeObject(utwor);
			view.printError(nazwa + " saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.enteredBarsNumberMake(idEnteredBarsNumber, model);
		

	}
/**
 * loades a utwor from a file
 * @param nazwa name of the file
 * @param idEnteredBarsNumber idEnteredBarsNumber the id of the text field for showing the number of bars in the utwor array
 * @param model reference to the model
 */
	
		@SuppressWarnings("unchecked")
		public void load(String nazwa, Text idEnteredBarsNumber, Model model) {
			// reading array from disk
			FileInputStream f_in;
			try {
				f_in = new FileInputStream(nazwa);
				@SuppressWarnings("resource")
				ObjectInputStream obj_in = new ObjectInputStream (f_in);
				utwor = (ArrayList <ArrayList <Element>>) obj_in.readObject();
				view.printError(nazwa + " loaded");
			} catch (FileNotFoundException e) {
				view.printError("Nie znaleziono pliku");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.enteredBarsNumberMake(idEnteredBarsNumber, model);
		}
	
}