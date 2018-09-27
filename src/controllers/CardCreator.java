package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_CardManager_Info;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class CardCreator extends Common_ControllerMethods{

    @FXML
    Button btnCreateUpdateCard;
    @FXML
    TextField tfCardTitle;
    @FXML
    TextArea taCardDescription;
    @FXML
    TextArea taCardCopyData;
    @FXML
    BorderPane bpContainer_All;
    @FXML
    StackPane spToast;
    private ToastCreator toast;
    private boolean editing;

    private FM_CardManager_XML cardXmlParser;
    private FM_CardManager_Info card;

    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);
        toast = new ToastCreator(spToast);

        editing = Constants.pref.getBoolean(Constants.PREF_SV_Boolean_Editing, false);

        if(editing){
            btnCreateUpdateCard.setText("Update");
            cardXmlParser = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_String_SelectedStack, null), false);
            card = cardXmlParser.getCards().get(Constants.pref.getInt(Constants.PREF_SV_String_SelectedCardPosition, 0));
            Constants.pref.putBoolean(Constants.PREF_SV_Boolean_Editing, false);

            tfCardTitle.setText(card.getCardTitle());
            taCardDescription.setText(card.getCardDescription());
            taCardCopyData.setText(card.getCardCopyData());
        }
    }

    public void btnAction_CreateCard(ActionEvent btn){
        if(!taCardCopyData.getText().isEmpty() && !editing){
            cardXmlParser = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_String_SelectedStack, null), false);
            card = new FM_CardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), taCardCopyData.getText());

            cardXmlParser.getCards().add(card);
            cardXmlParser.updateXMLFile();
            screen_changeDynamic(btn, Constants.FILE_FXML_StackViewer, bpContainer_All);
        }else if(!taCardCopyData.getText().isEmpty() && editing){
            card.setCardTitle(tfCardTitle.getText());
            card.setCardDescription(taCardDescription.getText());
            card.setCardCopyData(taCardCopyData.getText());
            cardXmlParser.updateXMLFile();

            screen_changeDynamic(btn, Constants.FILE_FXML_CardEditor, bpContainer_All);
        }else{
            toast.createShortToast("Fill in Data Filed");
        }
    }

    public void btnAction_Cancel(ActionEvent event){
        String scene;

        if(editing){
            screen_changeDynamic(event, Constants.FILE_FXML_CardEditor, bpContainer_All);
        }else{
            screen_checkAlwaysOnTop(Constants.PREF_SV_Boolean_AlwaysOnTop, event, FILE_FXML_StackViewer, bpContainer_All);
        }
    }
}
