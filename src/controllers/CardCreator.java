package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_CardManager_Info;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class CardCreator extends Common_ControllerMethods{

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

    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);

        toast = new ToastCreator(spToast);
    }

    public void btnAction_CreateCard(ActionEvent btn){
        if(!taCardCopyData.getText().isEmpty()){
            FM_CardManager_XML cardXmlParser = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_SelectedStack, null), false);
            FM_CardManager_Info card = new FM_CardManager_Info(tfCardTitle.getText(), taCardDescription.getText(), taCardCopyData.getText());

            cardXmlParser.getCards().add(card);
            cardXmlParser.updateXMLFile();
            screen_changeDynamic(btn, Constants.FILE_FXML_StackViewer, bpContainer_All);
        }else{
            toast.createToast("Fill in Data Filed");
        }
    }

    public void btnAction_Cancel(ActionEvent btn){
        screen_changeDynamic(btn, Constants.FILE_FXML_StackViewer, bpContainer_All);
    }
}
