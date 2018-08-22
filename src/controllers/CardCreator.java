package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_CardManager_Info;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CardCreator extends Common_ControllerMethods{

    @FXML
    TextField tfCardTitle;
    @FXML
    TextArea taCardDescription;
    @FXML
    TextArea taCardCopyData;

    @FXML
    public void initialize(){

    }

    @FXML
    public void btnAction_ShowUserInfo(ActionEvent e){
        // TODO COPY DATA WILL SAVED ONTOP YOUR CLIPBOARD TO BE ABLE TO PASTE INTO YOUR CODE
        // TODO SHOW SYMBOL THAT ONLY THESE FIELDS ARE REQUIRED TO BE  (MAYBE START EVERY PROGRAM WITH ONLY DEFAULT LOOK;
    }

    public void btnAction_CreateCard(ActionEvent btn){
        if(!taCardCopyData.getText().isEmpty()){
            FM_CardManager_XML cardXmlParser = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_CardViewTextFileName, null), false);
            FM_CardManager_Info card = new FM_CardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), taCardCopyData.getText());

            cardXmlParser.getCards().add(card);
            cardXmlParser.updateXMLFile();
            screen_changeNormalAlwaysOnTop(btn, Constants.FILE_FXML_StackViewer);
        }
    }

    public void btnAction_Cancel(ActionEvent btn){
        screen_changeNormalAlwaysOnTop(btn, Constants.FILE_FXML_StackViewer);
    }
}
