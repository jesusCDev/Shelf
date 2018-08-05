package Main;

import assets.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String xmlFile;
        if(Constants.pref.getBoolean(Constants.PREF_SV_FirstTimeUsingApp, true)){
            xmlFile = Constants.FILE_FXML_FileSetup;
            // make sure they complete this before passing in the value
            Constants.pref.putBoolean(Constants.PREF_SV_FirstTimeUsingApp, false);
        }else{
            xmlFile = Constants.FILE_FXML_Main;
        }

        Parent root = FXMLLoader.load(getClass().getResource(xmlFile));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(Constants.FILE_CSS).toExternalForm());
        primaryStage.setTitle(Constants.TEXT_PROJECT_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
