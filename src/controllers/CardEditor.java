package controllers;

import ControllerUI.Managers.CardEditor_ListViewCardManager;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.SnackBar;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Users can edit cards in the selected stacks by rearranging, editing, or deleting them.
 */
public class CardEditor extends Common_ControllerMethods implements Constants_Prefs{

    @FXML
    BorderPane bpAll;
    @FXML
    StackPane spToast;
    @FXML
    VBox vbContainer;
    @FXML
    Label lbTitle;

    private CardEditor_ListViewCardManager cardFileManager;

    /**
     * Sets title and window size
     * Sets toast message
     * Creates a card manager that:
     * * Creates card view using list
     * * Tracks card edits
     * * Commits or undo's changes
     */
    public void initialize(){
        screen_SetSize(bpAll);
        lbTitle.setText(new FM_StackManager_XML(false).getStack(pref.get(PREF_SV_String_SelectedStack, null)).getStackTitle());

        SnackBar sb = new SnackBar(spToast);
        sb.createSnackBar(Constants.TEXT_DragAndDropPA, SnackBar.LONG);

        cardFileManager = new CardEditor_ListViewCardManager(pref.get(PREF_SV_String_SelectedStack, null), vbContainer, bpAll, spToast);
        cardFileManager.createList();

    }

    /********** Onscreen Button Actions **********/

    /**
     * Reverts changes by coping data from a copy to the current file.
     * @param e
     */
    @FXML
    public void btnAction_GoBackToStackView(ActionEvent e){
        cardFileManager.undoChanges();
        screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, e, Constants.FILE_FXML_StackViewer, bpAll);
    }

    /**
     * Saves all changes done to current file
     * @param e
     */
    @FXML
    public void btnAction_SaveValuesGoBackToStackView(ActionEvent e){
        cardFileManager.saveCardStack();
        screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, e, Constants.FILE_FXML_StackViewer, bpAll);
    }
}
