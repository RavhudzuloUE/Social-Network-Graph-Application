
import csc3a.graph.gui.SocialNetworkGraphPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 *  *  SocialNetworkApp class is responsible for launching the desktop application to the user
 *  as it inherit from Application
 *
 */
public class SocialNetworkGraphApp extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		launch(args);  // Launch social network application 

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		SocialNetworkGraphPane socialNetworkPane = new SocialNetworkGraphPane(primaryStage);  // SocialNetworkGraphPane instance 
		                    
		Scene scene = new Scene(socialNetworkPane, 1300, 650);  // Instance of Scene
		
		primaryStage.setMinWidth(550);
		primaryStage.setMinHeight(500);
		primaryStage.setTitle("CODERS NETWORK");  // Setting the title of the Stage
		primaryStage.setScene(scene);             // Setting the Scene on the Stage
		primaryStage.show();   // Display the Stage
		
	}

}
