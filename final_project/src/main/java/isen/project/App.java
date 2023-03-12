package isen.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */

public class App extends Application {

    private static Scene scene;

    
    @Override
    public void start(Stage stage) throws IOException {
    
        scene = new Scene(loadFXML("/isen/project/view/firstView"), 600, 600);
        stage.setScene(scene);
        stage.setTitle("People");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

 	
 



    public static void main(String[] args) {
        launch();
    }
    
    
	

}
