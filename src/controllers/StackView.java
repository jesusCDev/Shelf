package controllers;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import ControllerUI.StackView_Manager.StackView_CardManager;
import ControllerUI.StackView_Manager.StackView_StackManager;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class StackView extends Common_ControllerMethods {

    @FXML
    VBox vbContainer;
    @FXML
    StackPane spToastMessanger;

    public void initialize(){
        new StackView_StackManager(vbContainer, spToastMessanger,
                Constants.pref.get(PREF_SV_StackViewList, null).split(","));
    }

    // BUTTON ACTIONS
    @FXML
    public void btnAction_CreateNewCard(ActionEvent e){
        screen_changeNormal(e, Constants.FILE_FXML_StackSelector);

//        screen_changeNormalTurnOfAlwaysOnTop(e, Constants.FILE_FXML_CardCreator);
    }

    @FXML
    public void btnAction_GoBackToMainMenu(ActionEvent e){
        Constants.pref.put(PREF_SV_StackViewList, "");
        screen_changeNormalTurnOfAlwaysOnTop(e, Constants.FILE_FXML_Main);
    }
}
