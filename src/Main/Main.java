package Main;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.Common_DefalutMethods;
import assets.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements Constants {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String xmlFile;
        if(pref.getBoolean(PREF_SV_FirstTimeUsingApp, true)){
            xmlFile = FILE_FXML_FileSetup;
        }else{
            // TODO CHECK IF THE FILE IS STILL HERE, CHECK IF IT WORKS
            // TODO CHECK OTHER FILES IF THEY WORK
            if(!checkIfMainFileExistAndWorks()){

            }
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
        Common_DefalutMethods cdm = new Common_DefalutMethods();
        cdm.resetValues();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private boolean checkIfMainFileExistAndWorks(){
        return true;
    }
}
