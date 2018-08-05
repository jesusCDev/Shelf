package assets;

import java.util.prefs.Preferences;

public interface Constants {

    String DOC_MainCard_XML = "MainCards.xml";
    String DOC_SubCard_XML = "SubCards.xml";

    String FILE_FXML_CardCreator = "/fxml/CardCreator.fxml";
    String FILE_FXML_Main = "/fxml/Main.fxml";
    String FILE_FXML_FileSetup = "/ControllerUI/FileFinder/FileSetUp.fxml";
    String FILE_FXML_CardViewer = "/fxml/CardViewer.fxml";

    String FILE_CSS = "/styles/styles.css";

    String TEXT_PROJECT_TITLE = "Shelf Comments";

    String PREF_SV_MainPath = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_MainPath";
    String PREF_SV_FirstTimeUsingApp = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_firstTimeUsingApp";
    String PREF_SV_ScreenWidth = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_width";
    String PREF_SV_ScreenHeight = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_height";
    String PREF_SV_ScreenMax = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_Maximized";

    String TEXT_FileChooserCanceled = "No File Picked";
    String TEXT_FileChooserError = "An Error Has Occured";

    Preferences pref = Preferences.userRoot();
}
