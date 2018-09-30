package ControllerUI.FirstTimeSetUp.FileFinder;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.Toast;
import ControllerUI.FirstTimeSetUp.FirstTimeSetUp;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;

public class FileSetUp extends Common_ControllerMethods {

    @FXML
    ListView<String> lv_FilesAndDirectories;
    @FXML
    TextField tf_FileLocation;
    private Toast toast;

    /**
     * Sets up default values
     */
    public void initialize(){
        lv_FilesAndDirectories.getItems().add(OuterFiles.DIR_MainFolder);
        lv_FilesAndDirectories.getItems().addAll(OuterFiles.PROJECT_DIRECTORIES);
        lv_FilesAndDirectories.getItems().addAll(OuterFiles.PROJECT_FILES);

        toast = new Toast();
    }

    /********** Onscreen Button Actions **********/

    /**
     * Presents user wtih a file explorer they can navigate to select the prefer location to install the app
     * @param e
     */
    @FXML
    public void btn_FileSystem_DirectoryFinder(ActionEvent e){
        // Open File Explorer
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);

        // Present Results
        if (result == JFileChooser.APPROVE_OPTION) {
            tf_FileLocation.setText(chooser.getSelectedFile().getAbsolutePath());
        }
        else if (result == JFileChooser.CANCEL_OPTION) {
            toast.showMessage(Constants.TEXT_FileChooserCanceled, Constants.WINDOW_TITLE_Error);
        }
        else if (result == JFileChooser.ERROR_OPTION) {
            toast.showMessage(Constants.TEXT_FileChooserError, Constants.WINDOW_TITLE_Error);
        }
    }

    /**
     * Submits user selected path
     * Sets up default values
     * @param e
     */
    @FXML
    public void btn_submitFileDestination(ActionEvent e){
        if(!tf_FileLocation.getText().isEmpty()){
            FirstTimeSetUp fts = new FirstTimeSetUp();
            String mainFilePath = fts.fixFilePath(tf_FileLocation.getText());

            if(fts.createFolderForProject(mainFilePath)){
                fts.setMainPathValue(mainFilePath);
                fts.setFirstTimePref(false);
                fts.createDefaultStacks();
                changeScreen(Common_ControllerMethods.CHANGE_SCREEN_NORMAL, Constants.FILE_FXML_Main, e, Constants.WINDOW_TITLE_Main,null, false);
            }else{
                // TODO TELL USER THE PLACE THEY PICKED DIDN'T DO ANYTHING
            }
        }
    }
}
