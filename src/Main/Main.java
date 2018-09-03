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
            // TODO CHECK IF THE FILE IS STILL HERE, CHECK IF IT WORKS
            // TODO CHECK OTHER FILES IF THEY WORK
            if(!checkIfMainFileExistAndWorks()){

            }
            xmlFile = Constants.FILE_FXML_Main;
        }

        Parent root = FXMLLoader.load(getClass().getResource(xmlFile));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(Constants.FILE_CSS).toExternalForm());
        primaryStage.setTitle(Constants.TEXT_PROJECT_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        Constants.pref.put(Constants.PREF_SV_StackViewList, "");
    }


    public static void main(String[] args) {
        launch(args);
    }

    private boolean checkIfMainFileExistAndWorks(){
        return true;
    }
}
