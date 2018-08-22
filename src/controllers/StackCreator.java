package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_MainCardManager_Info;
import FileHandler.FM_MainCardManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

public class StackCreator extends Common_ControllerMethods{

    @FXML
    Label lbCardTitle;
    @FXML
    TextField tfCardTitle;
    @FXML
    TextArea taCardDescription;
    @FXML
    Button btnCreateCard;

    private Boolean cardGettingEdited;
    private String cardID;
    private FM_MainCardManager_XML mainCardXmlParser;

    @FXML
    public void initialize(){

        cardGettingEdited = Constants.pref.getBoolean(Constants.PREF_SV_EditingCard, false);
        Constants.pref.putBoolean(Constants.PREF_SV_EditingCard, false);

        if(cardGettingEdited){
            cardID = Constants.pref.get(Constants.PREF_SV_CardViewTextFileName, null);
            btnCreateCard.setText("Update");

            mainCardXmlParser= new FM_MainCardManager_XML(false);
            lbCardTitle.setText(mainCardXmlParser.getCard(cardID).getCardTitle());
            tfCardTitle.setText(mainCardXmlParser.getCard(cardID).getCardTitle());
            taCardDescription.setText(mainCardXmlParser.getCard(cardID).getCardDescription());
        }
    }

    public void pm(String message){
        System.out.println(message);
    }
    public void btnActionCreateCard(ActionEvent btn){

        if(!tfCardTitle.getText().isEmpty() && !cardGettingEdited){
            // TODO When am i creating a new document
            mainCardXmlParser = new FM_MainCardManager_XML(false);
            pm("One");
            FM_MainCardManager_Info card = new FM_MainCardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), Boolean.toString(false), idCreator(16));
            FM_CardManager_XML cardXmlParser = new FM_CardManager_XML(card.getCardID(), true);
            cardXmlParser.createXMLFile();
            pm("Two");
            pm(card.toString());
            pm(mainCardXmlParser.toString());
            mainCardXmlParser.getCards().add(card);
            pm("Three");
            mainCardXmlParser.reorganizeCardAlphabetically();
            mainCardXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }else if(!tfCardTitle.getText().isEmpty() && cardGettingEdited){
            mainCardXmlParser.getCard(cardID).setCardTitle(tfCardTitle.getText());
            mainCardXmlParser.getCard(cardID).setCardDescrption(taCardDescription.getText());

            mainCardXmlParser.reorganizeCardAlphabetically();
            mainCardXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }else{
            // TODO TELL USER TO ADD AT LEAST A TITLE
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
