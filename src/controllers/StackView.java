package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.StackView_StackManager;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StackView extends Common_ControllerMethods {

    @FXML
    VBox vbContainer;
    @FXML
    StackPane spToast;
    @FXML
    BorderPane bpContainer_All;
    @FXML
    RadioButton rbTurnOnOffAlwaysOnTop;

    public void initialize(){
        screen_SetSize(bpContainer_All);

        rbTurnOnOffAlwaysOnTop.setSelected(Constants.pref.getBoolean(Constants.PREF_SV_Boolean_AlwaysOnTop, false));

        new StackView_StackManager(bpContainer_All, vbContainer, spToast,
                Constants.pref.get(PREF_SV_String_StackViewList, null).split(","));
    }

    // BUTTON ACTIONS
    @FXML
    public void btnAction_CreateNewCard(ActionEvent e){
        screen_changeDynamicAlwaysOffTop(e, Constants.FILE_FXML_StackSelector, bpContainer_All);

//        screen_changeNormalTurnOfAlwaysOnTop(e, Constants.FILE_FXML_CardCreator);
    }

    @FXML
    public void btnAction_GoBackToMainMenu(ActionEvent e){
        Constants.pref.put(PREF_SV_String_StackViewList, "");
        screen_changeDynamicAlwaysOffTop(e, Constants.FILE_FXML_Main, bpContainer_All);
    }

    public void rbTurnOnOffAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(!Constants.pref.getBoolean(Constants.PREF_SV_Boolean_AlwaysOnTop, false));
        Constants.pref.putBoolean(Constants.PREF_SV_Boolean_AlwaysOnTop, !Constants.pref.getBoolean(Constants.PREF_SV_Boolean_AlwaysOnTop, false));
    }
}
