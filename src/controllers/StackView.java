package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.StackView_StackManager;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Presents User With Selected Stacks
 */
public class StackView extends Common_ControllerMethods implements Constants_Prefs{

    @FXML
    VBox vbContainer;
    @FXML
    StackPane spToast;
    @FXML
    BorderPane bpContainer_All;
    @FXML
    RadioButton rbTurnOnOffAlwaysOnTop;

    /**
     * Sets Defaults
     * Shows User always on top setting status
     * Sets up stack view manager
     */
    @FXML
    public void initialize(){
        screen_SetSize(bpContainer_All);
        rbTurnOnOffAlwaysOnTop.setSelected(pref.getBoolean(PREF_SV_Boolean_AlwaysOnTop, false));

        new StackView_StackManager(bpContainer_All, vbContainer, spToast,
                pref.get(PREF_SV_String_StackViewList, null).split(Constants.SYMBOL_Comma));
    }

    /********** Onscreen Button Actions **********/

    /**
     * Forwards User to Card Creator Scene
     * @param e
     */
    @FXML
    public void btnAction_CreateNewCard(ActionEvent e){
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, Constants.FILE_FXML_StackSelector, e, bpContainer_All, false);
    }

    /**
     * Returns user to Stack View Scene
     * @param e
     */
    @FXML
    public void btnAction_GoBackToMainMenu(ActionEvent e){
        pref.put(PREF_SV_String_StackViewList, "");
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, Constants.FILE_FXML_Main, e, bpContainer_All, false);
    }

    /**
     * Switches the windows ability to float on-top of other windows
     * @param actionEvent
     */
    @FXML
    public void rbTurnOnOffAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(!pref.getBoolean(PREF_SV_Boolean_AlwaysOnTop, false));
        pref.putBoolean(PREF_SV_Boolean_AlwaysOnTop, !pref.getBoolean(PREF_SV_Boolean_AlwaysOnTop, false));
    }
}
