package main;

import controller_ui.deafult_methods.Common_DefaultMethods;
import file_manager.FM_FileChecker;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A project design to help manage code by keeping snippets of code blocks that can be re-used in order to organize code
 */
public class Main extends Application implements Constants_Prefs, Constants {

    Common_DefaultMethods cdm = new Common_DefaultMethods();

    @Override
    public void start(Stage primaryStage) throws Exception{
        String xmlFile;
        FM_FileChecker ffc = new FM_FileChecker();

        if(pref.getBoolean(PREF_SV_Boolean_FirstTimeUsingApp, true) && ffc.checkIfMainDocumentExistOrHasBeenMessWith()){
            xmlFile = FILE_FXML_FileSetup;
            cdm.hardRest();
        }else{
            ffc.checkIfStacksExistOrHaveBeenMessedWith();
            xmlFile = FILE_FXML_Main;
        }

        Parent root = FXMLLoader.load(getClass().getResource(xmlFile));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        primaryStage.setTitle(TEXT_PROJECT_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        cdm.resetValues();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
