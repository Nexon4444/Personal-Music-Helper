package application;

import Controllers.ProjektController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Nexon
 * Class that is the first class launched when application starts working.
 * Creates objects of Model, View ond MenuController classes.
 * 
 */

public class Start extends Application {


	public static void main(String[] args) {
		launch(args);
	}


	public void start(Stage primaryStage) throws Exception {
		
		View view = new View(primaryStage);
		Model model = new Model(view);
		ProjektController projektController = view.getProjektController();
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		projektController.setView(view);
		projektController.setModel(model);
		view.setIdErrorText(projektController.getIdErrorText()); 
	}
}
