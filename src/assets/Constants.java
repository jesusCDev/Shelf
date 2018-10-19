package assets;

/**
 * Constants Used Throughout Project
 */
public interface Constants {

    /********** Default Values **********/
    int STACK_SIZE = 300;
    int defaultWindowWidth = 800;
    int defaultWindowHeight = 600;
    int defaultToastWidth = 300;
    int defaultToastHeight = 100;

    /********** File/Dir Names **********/
    String FOLDER_Shelf = "Shelf";
    String DOC_Stack_XML = "Stack.xml";

    /********** Messages **********/
    String TOAST_MESSAGE_OKAY = "Okay";

    /********** Symbols **********/
    String SYMBOL_Comma = ",";
    String SYMBOL_Underscore = "_";
    char SYMBOL_BackSlash = '/';
    char SYMBOL_FrontSlash = '\\';

    /********** Text Saved On Used In Project **********/
    String TEXT_PROJECT_TITLE = "Shelf";

    /********** Messages To Show User **********/
    String TEXT_FileChooserCanceled = "No File Picked";
    String TEXT_FileChooserError = "An Error Has Occurred";
    String TEXT_FillData = "Fill In Data TextArea";
    String TEXT_FillTitle = "Fill In Title TextField";
    String TEXT_Update = "Update";
    String TEXT_DragAndDropPA = "You Can Move Files Up And Down By Dragging and Dropping.";

    /********** File References **********/
    String FILE_FXML_Main = "/fxml/Home.fxml";
    String FILE_FXML_FileSetup = "/controller_ui/installer/installer/Installer.fxml";
    String FILE_FXML_StackViewer = "/fxml/StackView.fxml";
    String FILE_FXML_StackCreator = "/fxml/StackCreator.fxml";
    String FILE_FXML_StackSelector = "/fxml/StackSelector.fxml";
    String FILE_FXML_CardCreator = "/fxml/CardCreator.fxml";
    String FILE_FXML_CardEditor = "/fxml/CardEditor.fxml";
    String FILE_CSS = "/styles/styles.css";


    /********** Window Titles **********/
    String WINDOW_TITLE_Error = "Error";
    String WINDOW_TITLE_Main = "Shelf";
    String WINDOW_TITLE_StackViewer = "Stack Viewer";
    String WINDOW_TITLE_StackCreator = "Stack Creator";
    String WINDOW_TITLE_StackSelector = "Stack Selector";
    String WINDOW_TITLE_CardCreator = "Card Creator";
    String WINDOW_TITLE_CardEditor = "Card Editor";
}
