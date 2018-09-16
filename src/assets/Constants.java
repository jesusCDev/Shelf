package assets;

import java.util.prefs.Preferences;

public interface Constants {

    String DOC_Stack_XML = "Stack.xml";

    String FILE_FXML_Main = "/fxml/Home.fxml";
    String FILE_FXML_FileSetup = "/ControllerUI/FirstTimeSetUp/FileFinder/FileSetUp.fxml";
    String FILE_FXML_StackViewer = "/fxml/StackView.fxml";
    String FILE_FXML_StackCreator = "/fxml/StackCreator.fxml";
    String FILE_FXML_StackSelector = "/fxml/StackSelector.fxml";
    String FILE_FXML_CardCreator = "/fxml/CardCreator.fxml";

    String FILE_CSS = "/styles/styles.css";

    String TEXT_PROJECT_TITLE = "Shelf Comments";

    String PREF_SV_SelectedStack = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_HoldsASelectedClass";
    String PREF_SV_FirstTimeUsingApp = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_firstTimeUsingApp"; // value to check if this is the first time the user is using the app
    String PREF_SV_MainPath = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_MainPath"; // Path to main folder where app is located at
    String PREF_SV_StackViewList = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_HoldsMultipleClassesThatAreOpen"; // Holds the names of the
    String PREF_SV_Editing = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_Editing"; // Keep the id of the the stack or card that is being edited TODO MIGHT NEED TO HAVE SEPERATE FOR STACKS AND FOR CARD EDITS

    String PREF_SV_ScreenWidth = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_width";
    String PREF_SV_ScreenHeight = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_height";
    String PREF_SV_ScreenMax = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_Maximized";

    String TEXT_FileChooserCanceled = "No File Picked";
    String TEXT_FileChooserError = "An Error Has Occured";

    Preferences pref = Preferences.userRoot();

}
