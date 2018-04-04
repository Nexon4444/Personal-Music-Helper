package application;

import java.io.IOException;
import java.util.ArrayList;

import Controllers.ProjektController;
import Elements.Czas;
import Elements.Element;
import Elements.Nuta;
import Elements.Pauza;
import Elements.ToggleButtonExtended;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class which methods are used to load Scenes and update elements on the screen
 * 
 * @author Maciej_Kutrowski
 *
 */
public class View {
	
	private Text idErrorText;
	private int iloscGuzikowWHBox = 8;
	private double ToggleButtonExtendedMaxWidth=50.0;
	private double ToggleButtonExtendedMaxHeight=35.0;
	private double Xmin = 130;
	private double Xmax = 505;
	protected double Ymin;
	
	private ArrayList<Double> myHeigthSets = new ArrayList<Double>();
	private ArrayList<Double> myWidthSets = new ArrayList<Double>();
	private ArrayList<Double> offsetXSets = new ArrayList<Double>();
	private ArrayList<Double> offsetYSets = new ArrayList<Double>();
	
/**
 * sets the lowest note value
 * @param ymin note value to be set
 */
	public void setYmin(double ymin) {
		Ymin = ymin-30;
	}

	private double szerokosc;
	private double lastPoz;
	private ArrayList<ImageView> ListaElementow = new ArrayList<ImageView>();
	String graphicsForToggleButtonExtendeds[] = new String[iloscGuzikowWHBox];
	/**
	 * Variable holding object loaded from FXML file.
	 */
	private FXMLLoader loader;

	/**
	 * Variable holding main Pane to which new elements can be added
	 */
	private static Pane mainPane;

	/**
	 * Constructor loads the FXML file
	 * @param primaryStage
	 *            stage to which all scenes will be added
	 */

	
	public View(Stage primaryStage) {
		setDefaultStageSettings(primaryStage);
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ProjektFXML.fxml")); 
		this.loader = loader;
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane = pane;
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		szerokosc = Xmax-Xmin;
		lastPoz = Xmin;
		constantsMake();
		
		
	}
	
/**
 * Initializes text in the given text fields
 * @param idEnteredBarsNumber to be set
 * @param idEnterText to be set
 */
/*	public void viewInitialize(Text idEnteredBarsNumber, TextField idEnterText)
	{
		idEnteredBarsNumber.setText("0");
		idErrorText.setFont(new Font("Cambria", 15));
		idEnterText.setFont(new Font("Cambria", 15));
	}*/
/**
 * generates constants used for positioning notes and rests
 */
	private void constantsMake()
	{
		myHeigthSets.add(50.0); // CalaNuta
		myHeigthSets.add(50.0); // PolNuta
		myHeigthSets.add(50.0); // CwiercNuta
		myHeigthSets.add(60.0); // OsemkaNuta
		
		myHeigthSets.add(40.0); // CalaPauza
		myHeigthSets.add(40.0); // PolPauza
		myHeigthSets.add(65.0); // CwiercPauza
		myHeigthSets.add(45.0); // OsemkaPauza
		
		
		myWidthSets.add(30.0); // CalaNuta
		myWidthSets.add(30.0); // PolNuta
		myWidthSets.add(20.0); // CwiercNuta
		myWidthSets.add(30.0); // OsemkaNuta
		
		myWidthSets.add(20.0); // CalaPauza
		myWidthSets.add(20.0); // PolPauza
		myWidthSets.add(50.0); // CwiercPauza
		myWidthSets.add(45.0); // OsemkaPauza
		
		
		offsetXSets.add(0.0); // CalaNuta
		offsetXSets.add(0.0); // PolNuta
		offsetXSets.add(0.0); // CwiercNuta
		offsetXSets.add(0.0); // OsemkaNuta
		
		offsetXSets.add(0.0); // CalaPauza
		offsetXSets.add(0.0); // PolPauza
		offsetXSets.add(0.0); // CwiercPauza
		offsetXSets.add(0.0); // OsemkaPauza
		
		
		offsetYSets.add(98.5); // CalaNuta
		offsetYSets.add(100.0); // PolNuta
		offsetYSets.add(100.0); // CwiercNuta
		offsetYSets.add(90.0); // OsemkaNuta
		
		offsetYSets.add(94.0); // CalaPauza
		offsetYSets.add(91.0); // PolPauza
		offsetYSets.add(90.0); // CwiercPauza
		offsetYSets.add(98.0); // OsemkaPauza
		
	
	}
/**
 * Translates the note pitch value into the position of a note
 * @param i the pitch value of a note
 * @return position of a note on the staff
 */
	private int translatePosition(int i)
	{
		switch (i) {
		case 60:
			return 0;
		case 62:
			return 1;	
		case 64:
			return 2;
		case 65:
			return 3;
		case 67:
			return 4;
		case 69:
			return 5;
		case 71:
			return 6;
		case 72:
			return 7;
		case 74:
			return 8;
		case 76:
			return 9;
		case 77:
			return 10;
		case 80:
			return 11;
			
		default:
			return 0;
			
		}
	}
	/**
	 * Sets default stage settings
	 * 
	 * @param primaryStage
	 *            stage to which all scenes will be added
	 */
	private void setDefaultStageSettings(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
//		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/BLUE.png"))); // IKONKA
		primaryStage.setTitle("Personal Music Helper");
		primaryStage.centerOnScreen();
	}


/**
 * setter for loader
 * @param loader a loader to be set
 */
	private void setLoader(FXMLLoader loader) {
		this.loader = loader;
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().add(pane);
	}
	


/**
 * getter for projektController
 * @return references of the project controller
 */
	public ProjektController getProjektController() {
		ProjektController projektController = this.loader.getController();
		return projektController;
	}
	

/**
 * Loads ToggleButtonExtended to the HBox, and sets their standard width and higth
 * @param hBox reference to the hBox to which the ToggleButtonsExtended should be added
 * @return a reference to the added ToggleButtonsExtendened array
 */
	public ToggleButtonExtended[] loadButtons(HBox hBox) {
		ToggleButtonExtended[] ToggleButtonExtendeds = new ToggleButtonExtended[iloscGuzikowWHBox];
		Element[] elements;
		elements = new Element[] {new Nuta(Czas.CalaNuta),
								  new Nuta(Czas.PolNuta),
								  new Nuta(Czas.CwiercNuta),
								  new Nuta(Czas.OsemkaNuta),
								  new Pauza(Czas.CalaPauza),
								  new Pauza(Czas.PolPauza),
								  new Pauza(Czas.CwiercPauza),
								  new Pauza(Czas.OsemkaPauza)};
		
		for (int i = 0; i < iloscGuzikowWHBox; i++) {
				ToggleButtonExtendeds[i] = new ToggleButtonExtended(elements[i]);
				ToggleButtonExtendeds[i].setPrefSize(ToggleButtonExtendedMaxWidth, ToggleButtonExtendedMaxWidth);
				hBox.getChildren().add(ToggleButtonExtendeds[i]);
				}
		return ToggleButtonExtendeds;
		
	}
/**
 * Sets standard options for the imageViews in the toggleButtonExtendeds
 * @param model referenced model
 * @param imageView referenced imagView
 * @param i ordinal number of the toggleButtonExtended in the toggleButtonExtendeds array
 * @param maxWidth max width for the imageView
 * @param maxHeigth max height for the imageView
 */
	public void setStandardsForImageView(Model model, ImageView imageView, int i, double maxWidth, double maxHeigth)
	{
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(maxHeigth);
		imageView.setFitWidth(maxWidth);
		model.toggleButtonExtendeds[i].setGraphic(imageView);
		model.toggleButtonExtendeds[i].setToggleGroup(model.group);
		
		model.toggleButtonExtendeds[i].setPrefWidth(100); //
		
		model.toggleButtonExtendeds[i].setMyHeight(myHeigthSets.get(i));
		model.toggleButtonExtendeds[i].setMyWidth(myWidthSets.get(i));
		
		model.toggleButtonExtendeds[i].setOffsetX(offsetXSets.get(i));
		model.toggleButtonExtendeds[i].setOffsetY(offsetYSets.get(i));
		
		model.toggleButtonExtendeds[0].setSelected(true);
	}
	
/**
 * loads the graphics for the {@link ToggleButtonExtended} into their objects
 * @param model referenced model
 */
	public void graphicsForToggleButtonExtendsMake(Model model)
	{
		graphicsForToggleButtonExtendeds[0] = "/Images/CalaNuta2.gif";
		graphicsForToggleButtonExtendeds[1] = "/Images/PolNuta2.gif";
		graphicsForToggleButtonExtendeds[2] = "/Images/CwiercNuta2.gif";
		graphicsForToggleButtonExtendeds[3] = "/Images/OsemkaNuta2.gif";
		graphicsForToggleButtonExtendeds[4] = "/Images/CalaPauza.gif";
		graphicsForToggleButtonExtendeds[5] = "/Images/PolPauza.gif";
		graphicsForToggleButtonExtendeds[6] = "/Images/CwiercPauza.gif";
		graphicsForToggleButtonExtendeds[7] = "/Images/OsemkaPauza.png";
		
		for (int i = 0; i < iloscGuzikowWHBox;i++)
		{
			model.toggleButtonExtendeds[i].setLinkToFile(graphicsForToggleButtonExtendeds[i]);
			
		}
	}
	
/**
 * Using methods graphicsForToggleButtonExtendsMake and setStandardsForImageView, loads the graphics of {@link ToggleButtonExtended} sets the images for each
 * imageView
 * @param model referenced model
 */
	public void setToggleButtonExtendedsImages(Model model)
	{
		
		graphicsForToggleButtonExtendsMake(model);
		for (int i = 0; i < iloscGuzikowWHBox; i++) 
			{
				setStandardsForImageView(model, new ImageView(new Image(getClass().getResourceAsStream(graphicsForToggleButtonExtendeds[i].toString()))), i, ToggleButtonExtendedMaxWidth, ToggleButtonExtendedMaxHeight);
			}
		
		
	}

/**
 * adds Nuta object to the staff {@link ImageView}, using the {@link ToggleGroup} group, 
 * @param model referenced {@link Model}
 * @param nuta referenced {@link Nuta}
 */
	public void addNuta(Model model, Nuta nuta) {
		double value;
		ToggleButtonExtended temp = (ToggleButtonExtended)model.group.getSelectedToggle();
		Image image = new Image(temp.getLinkToFile());
		
		ImageView imageView = new ImageView(image);
		if(model.isFirstNote()) value = temp.getElement().getCzasTrwania()/2;
		
		else value = temp.getElement().getCzasTrwania();
		double X = lastPoz+value*szerokosc;
		lastPoz = X;
		double Y = Ymin-(translatePosition(nuta.getWysokosc().getValue())*model.getJumpVal());

		imageView.setFitHeight(temp.getMyHeight());
		imageView.setFitWidth(temp.getMyWidth());
		System.out.println("poz X: " + X + "\npoz Y: " + Y);
		imageView.setX(X+temp.getOffsetX());
		imageView.setY(Y+temp.getOffsetY());
		ListaElementow.add(imageView);
		mainPane.getChildren().add(imageView);
		
	}
/**
 * adds Pauza object to the staff {@link ImageView}, using the {@link ToggleGroup} group, 
 * @param model referenced {@link Model}
 * @param pauza referenced {@link Pauza}
 */
	public void addPauza(Model model, Pauza pauza) {
		
		double value;
		ToggleButtonExtended temp = (ToggleButtonExtended)model.group.getSelectedToggle();
		Image image = new Image(temp.getLinkToFile());
		
		ImageView imageView = new ImageView(image);
		if(model.isFirstNote())
			value = temp.getElement().getCzasTrwania()/2;
		
		else value = temp.getElement().getCzasTrwania();
		double X = lastPoz+value*szerokosc;
		lastPoz = X;
		double Y = temp.getOffsetY();

		imageView.setFitHeight(temp.getMyHeight());
		imageView.setFitWidth(temp.getMyWidth());
		System.out.println("poz X: " + X + "\npoz Y: " + Y);
		imageView.setX(X+temp.getOffsetX());
		imageView.setY(Y+temp.getOffsetY());
		ListaElementow.add(imageView);
		mainPane.getChildren().add(imageView);
	}
/**
 * deletes all the {@link Nuta} and {@link Pauza} objects from the staff imageView
 */
	public void deleteElementsOfEnteredBar() {
		for (ImageView imageView : ListaElementow)
		{
			mainPane.getChildren().remove(imageView);
		}
		ListaElementow.clear();
		lastPoz = Xmin;
		
	}
/**
 * calls deleteElementsOfEnteredBar method and displays message
 */
	public void clear() {
		deleteElementsOfEnteredBar();
		printError("Bar cleared");
	}

	/**
	 * Sets text on the {@link Text} object
	 * @param idEnteredBarsNumber id of the element assigned to show the number of bars
	 * @param model referenced {@link Model}
	 */
	public void enteredBarsNumberMake(Text idEnteredBarsNumber, Model model)
	{
		idEnteredBarsNumber.setText(model.getBarsEntered().toString());
	}
	
/**
 * calls the enteredBarsNumberMake method, thus sets the text on the {@link Text} object with the id idEnteredBarsNumber
 * @param idEnteredBarsNumber id of the element assigned to show the number of bars
 * @param model referenced {@link Model}
 */
	public void enterBar(Text idEnteredBarsNumber, Model model) {
		enteredBarsNumberMake(idEnteredBarsNumber, model);
		
	}
	/**
	 * calls the enteredBarsNumberMake method, thus sets the text on the {@link Text} object with the id idEnteredBarsNumber
	 * to "0"
	 * @param idEnteredBarsNumber id of the element assigned to show the number of bars
	 */
	public void reset(Text idEnteredBarsNumber) {
		idEnteredBarsNumber.setText("0");
		
	}
/**
 * setter for idErrorText
 * @param idErrorText id to be set
 */
	public void setIdErrorText(Text idErrorText) {
		this.idErrorText = idErrorText;
	}
/**
 * prints messages for the user on the idErrorText element
 * @param string message to be printed
 */
	public void printError(String string)
	{
		idErrorText.setText(string);
	}

}
