package Controllers;

import application.Model;

import application.View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Nexon
 * MainController for the program. Controls all the buttons and clicking on the imageView with the staff
 */
public class ProjektController {
@FXML
private Model model;

@FXML
private View view;

@FXML
private ImageView idPieciolinia;

@FXML
private HBox idHBox;

@FXML
private Text idEnteredBarsNumber;

@FXML
private Text idErrorText;

/**
 * getter for idErrorText
 * @return {@link Text} idErrorText
 */
public Text getIdErrorText() {
	return idErrorText;
}

@FXML
private TextField idEnterText;
/**
 * setter for model
 * @param model to be set
 */
	public void setModel(Model model) {
		this.model= model;
		
	}
/**
 * setter for view
 * @param view to be set
 */
	public void setView(View view) {
		this.view=view;
	}
/**
 * react to the event of the staff being clicked
 * @param event click event
 */
	@FXML
    void idPiecioliniaMouseClicked(MouseEvent event) {
		System.out.println("X =" + event.getX());
		System.out.println("Y =" + event.getY());
		model.addElement(event);
		//view.addElement(event);
		
    }
/**
 * 	initialize how text fields look using viewInitialize method
 */
	  @FXML
	    void initialize() {
		  
		//  view.viewInitialize(idEnteredBarsNumber, idEnterText);

	  }

	  
/**
 * getter for idHBox
 * @return idHBox
 */
	public HBox getIdHBox() {
		return idHBox;
	}
	
/**
 * Handles clicking the Enter Bar button, call methods in view and model
 * @param event of clicking the button
 */
    @FXML
    void idEnterOnAction(ActionEvent event) {
    	model.enterBar();
    	view.enterBar(idEnteredBarsNumber, model);
    }
    /**
     * Handles clicking the play button, call methods in model
     * @param event event of clicking the button
     */
    @FXML
    void idPlayOnAction(ActionEvent event) {
    	model.play();
    }
/**
 * exits program
 * @param event of clicking the exit button
 */
    @FXML
    void idReturnOnAction(ActionEvent event) {
    	Platform.exit();
    }
/**
 * saves score
 * @param event of clicking the button save
 */
    @FXML
    void idSaveOnAction(ActionEvent event) {
    	model.save(idEnterText.getText(), idEnteredBarsNumber, model);
    }
    
/**
 * loads file with saved data
 * @param event of clicking the button load
 */
    @FXML
    void idLoadOnAction(ActionEvent event) {
    	model.load(idEnterText.getText(), idEnteredBarsNumber, model);
    }
  /**
   * resets the current score
   * @param event of clicking the button reset
   */
    @FXML
    void idResetOnAction(ActionEvent event) {
    	model.reset();
    	view.reset(idEnteredBarsNumber);
    }
  /**
   * clears the staff
   * @param event of clicking the button clear
   */
    @FXML
    void idClearOnAction(ActionEvent event) {
    	model.clear();
    	view.clear();
    }
}
	  

	  

