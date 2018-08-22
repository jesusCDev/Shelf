package ControllerUI.FileFinder;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import assets.OutterFiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.File;

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
        screen_changeNormal(e, FILE_FXML_Main);
//
//        if(!tf_FileLocation.getText().isEmpty()){
//            File dir = new File(tf_FileLocation.getText() + File.separator + OutterFiles.DIR_MainFolder);
//            boolean dirMade = false;
//            if(!dir.exists()){
//                dirMade = dir.mkdir();
//            }
//
//            if(dirMade){
//                // create directories
//                createDirectoreis(dir.getAbsolutePath(),  OutterFiles.PROJECT_DIRECTORIES);
//                // Create Default Languages
//
//                // Save values
//                // Change Screens
//                screen_changeNormal(e, FILE_FXML_Main);
//            }else{
//                toast_Message(TEXT_FileChooserError);
//            }
//        } else {
//            toast_Message("Set A Directory Folder");
//        }
    }

    private void createDirectoreis(String path, String[] dirs){
        for(String dir: dirs){
            new File(path + File.separator + dir).mkdir();
        }
    }
}
