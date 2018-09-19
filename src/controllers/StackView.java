package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.StackView_StackManager;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StackView extends Common_ControllerMethods {

    @FXML
    VBox vbContainer;
    @FXML
    StackPane spToastMessanger;
    @FXML
    BorderPane bpContainer_All;

    public void initialize(){
        screen_SetSize(bpContainer_All);
        new StackView_StackManager(vbContainer, spToastMessanger,
                Constants.pref.get(PREF_SV_StackViewList, null).split(","));
    }

    // BUTTON ACTIONS
    @FXML
    public void btnAction_CreateNewCard(ActionEvent e){
        screen_changeDynamic(e, Constants.FILE_FXML_StackSelector, bpContainer_All);

//        screen_changeNormalTurnOfAlwaysOnTop(e, Constants.FILE_FXML_CardCreator);
    }

    @FXML
    public void btnAction_GoBackToMainMenu(ActionEvent e){
        Constants.pref.put(PREF_SV_StackViewList, "");
        screen_changeDynamic(e, Constants.FILE_FXML_Main, bpContainer_All);
    }
}
