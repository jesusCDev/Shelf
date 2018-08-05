package controllers;

import ControllerUI.Common_ControllerMethods;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

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

    public void btnActionCreateCard(ActionEvent btn){
        if(!taCardCopyData.getText().isEmpty()){
            FM_CardManager_XML cardXmlParser = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_CardViewTextFileName, null), false);
            FM_CardManager_Info card = new FM_CardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), taCardCopyData.getText());

            cardXmlParser.getCards().add(card);
            cardXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }
    }

    public void btnActionCancel(ActionEvent btn){
        screen_changeNormal(btn, Constants.FILE_FXML_CardViewer);
    }
}
