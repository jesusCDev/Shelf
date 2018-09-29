package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.StackSelector_StackManager;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Allows users to select as many stacks as they wish to be presented in stack view
 */
public class StackSelector extends Common_ControllerMethods implements Constants_Prefs{

    @FXML
    VBox vb_FavStacksContainer;
    @FXML
    VBox vb_NonFavStacksContainer;
    @FXML
    BorderPane bpContainer_All;

    private StackSelector_StackManager ss;

    /**
     * Creates col view for the stacks
     */
    @FXML
    public void initialize(){
        screen_SetSize(bpContainer_All);

        ss = new StackSelector_StackManager();
        ss.setFavVBoxContainer(vb_FavStacksContainer);
        ss.setNonFavVBoxContainer(vb_NonFavStacksContainer);
        ss.createCols(Constants.defaultWindowWidth);
    }

    /********** Onscreen Button Actions **********/

    /**
     * Returns user to Stack View Scene
     * @param e
     */
    @FXML
    public void btnAction_Cancel(ActionEvent e){
        screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, e, Constants.FILE_FXML_StackViewer, bpContainer_All);
    }

    /**
     * Saves selected stacks
     * @param e
     */
    @FXML
    public void btnAction_Done(ActionEvent e){
        ss.saveSelectedStacks();
        screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, e, Constants.FILE_FXML_StackViewer, bpContainer_All);
    }
}
