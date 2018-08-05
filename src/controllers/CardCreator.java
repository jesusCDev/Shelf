package controllers;

import ControllerUI.Common_ControllerMethods;
import FileHandler.FM_MainCardManager_Info;
import FileHandler.FM_MainCardManager_XML;
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
    TextArea tfCardDescription;

    @FXML
    public void initialize(){

    }

    public void btnActionCreateCard(ActionEvent btn){
        if(!tfCardDescription.getText().isEmpty() && !tfCardTitle.getText().isEmpty()){
            FM_MainCardManager_XML xmlParser = new FM_MainCardManager_XML(false);
            xmlParser.getCards().add(new FM_MainCardManager_Info(tfCardTitle.getText(), tfCardDescription.getText(), Boolean.toString(false), idCreator(16)));
            xmlParser.reorganizeCardAlphabetically();
            xmlParser.updateXMLFile();
        }
        screen_changeNormal(btn, Constants.FILE_FXML_Main);
    }

    public void btnActionCancel(ActionEvent btn){

    }

    private String idCreator(int length){
        StringBuilder sb = new StringBuilder();
        Random ranNum = new Random();

        for(int i = 0; i < length; i++){
            sb.append(ranNum.nextInt(Integer.MAX_VALUE));
        }
        return sb.toString();
    }
}
