package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.SnackBar;
import ControllerUI.DefaultMethods.UI_Feedback.Toast;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_CardManager_Info;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * User can create cards that belong to a stack
 */
public class CardCreator extends Common_ControllerMethods implements Constants_Prefs, Constants {

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

    private boolean editing;
    private Toast toast;

    private FM_CardManager_XML cardXmlParser;
    private FM_CardManager_Info card;

    /**
     *  Set Project Defaults
     *  Sets Up Card Basics
     */
    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);
        editing = pref.getBoolean(PREF_SV_Boolean_Editing, false);
        toast = new Toast();

        if(editing){
            cardXmlParser = new FM_CardManager_XML(pref.get(PREF_SV_String_SelectedStack, null), false);
            card = cardXmlParser.getCards().get(pref.getInt(PREF_SV_String_SelectedCardPosition, 0));

            btnCreateUpdateCard.setText(TEXT_Update);
            pref.putBoolean(PREF_SV_Boolean_Editing, false);


            tfCardTitle.setText(card.getCardTitle());
            taCardDescription.setText(card.getCardDescription());
            taCardCopyData.setText(card.getCardCopyData());
        }
    }

    /********** Onscreen Button Actions **********/

    /**
     * Creates card onto stack that has been selected
     * Updates card that has been selected
     * @param e
     */
    @FXML
    public void btnAction_CreateCard(ActionEvent e){
        if(!taCardCopyData.getText().isEmpty() && !editing){

            cardXmlParser = new FM_CardManager_XML(pref.get(PREF_SV_String_SelectedStack, null), false);
            card = new FM_CardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), taCardCopyData.getText());

            cardXmlParser.getCards().add(card);
            cardXmlParser.updateXMLFile();

            changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_StackViewer, e, bpContainer_All, false);
        }else if(!taCardCopyData.getText().isEmpty() && editing){
            card.setCardTitle(tfCardTitle.getText());
            card.setCardDescription(taCardDescription.getText());
            card.setCardCopyData(taCardCopyData.getText());

            cardXmlParser.updateXMLFile();
            changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_CardEditor, e, bpContainer_All, false);
        }else{
            toast.showMessage(TEXT_FillData, Constants.WINDOW_TITLE_Error);
        }
    }

    /**
     * Returns user to StackView Scene
     * @param e
     */
    @FXML
    public void btnAction_Cancel(ActionEvent e){
        if(editing){
            changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_CardEditor, e, bpContainer_All, false);
        }else{
            screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, e, FILE_FXML_StackViewer, bpContainer_All);
        }
    }
}
