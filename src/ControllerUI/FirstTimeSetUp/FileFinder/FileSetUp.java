package ControllerUI.FirstTimeSetUp.FileFinder;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.FirstTimeSetUp.FirstTimeSetUp;
import assets.OutterFiles;
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

    public void initialize(){

        lv_FilesAndDirectories.getItems().add(OutterFiles.DIR_MainFolder);
        lv_FilesAndDirectories.getItems().addAll(OutterFiles.PROJECT_DIRECTORIES);
        lv_FilesAndDirectories.getItems().addAll(OutterFiles.PROJECT_FILES);

    }

    /********** BUTTON ACTIONS **********/

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
            toast_Message(TEXT_FileChooserCanceled);
        }
        else if (result == JFileChooser.ERROR_OPTION) {
            toast_Message(TEXT_FileChooserError);
        }
    }

    @FXML
    public void btn_submitFileDestination(ActionEvent e){

        if(!tf_FileLocation.getText().isEmpty()){
            FirstTimeSetUp fts = new FirstTimeSetUp();
            String mainFilePath = fts.fixFilePath(tf_FileLocation.getText());

            if(fts.createFolderForProject(mainFilePath)){
                fts.setMainPathValue(mainFilePath);
                fts.setFirstTimePref(false);
                fts.createDefaultStacks();
                screen_changeNormal(e, FILE_FXML_Main);
            }
        }
    }
}
