package controllers;

import ControllerUI.Common_ControllerMethods;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_MainCardManager_Info;
import FileHandler.FM_MainCardManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

public class MainCardCreator extends Common_ControllerMethods{

    @FXML
    TextField tfCardTitle;
    @FXML
    TextArea tfCardDescription;

    @FXML
    public void initialize(){

    }

    public void btnActionCreateCard(ActionEvent btn){
        if(!tfCardDescription.getText().isEmpty() && !tfCardTitle.getText().isEmpty()){
            FM_MainCardManager_XML mainCardXmlParser = new FM_MainCardManager_XML(false);
            FM_MainCardManager_Info card = new FM_MainCardManager_Info(tfCardTitle.getText(), tfCardDescription.getText(), Boolean.toString(false), idCreator(16));
            FM_CardManager_XML cardXmlParser = new FM_CardManager_XML(card.getCardID(), true);
            cardXmlParser.createXMLFile();

            mainCardXmlParser.getCards().add(card);
            mainCardXmlParser.reorganizeCardAlphabetically();
            mainCardXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }
    }

    public void btnActionCancel(ActionEvent btn){
        screen_changeNormal(btn, Constants.FILE_FXML_Main);
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
