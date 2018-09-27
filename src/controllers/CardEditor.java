package controllers;

import ControllerUI.Managers.CardEditor_ListViewCardManager;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CardEditor extends Common_ControllerMethods{

    @FXML
    BorderPane bpAll;
    @FXML
    StackPane spToast;
    @FXML
    VBox vbContainer;
    @FXML
    Label lbTitle;

    private CardEditor_ListViewCardManager celvcm;

    public void initialize(){
        screen_SetSize(bpAll);
        lbTitle.setText(new FM_StackManager_XML(false).getStack(Constants.pref.get(Constants.PREF_SV_String_SelectedStack, null)).getStackTitle());
        celvcm = new CardEditor_ListViewCardManager(Constants.pref.get(Constants.PREF_SV_String_SelectedStack, null), vbContainer, bpAll, spToast);
        celvcm.createList();
        ToastCreator tc = new ToastCreator(spToast);
        tc.crearteLongToast("You Can Move Files Up And Down By Dragging and Dropping.");
    }

    @FXML
    public void btnAction_GoBackToStackView(ActionEvent event){
        celvcm.undoChanges();
        screen_checkAlwaysOnTop(Constants.PREF_SV_Boolean_AlwaysOnTop, event, FILE_FXML_StackViewer, bpAll);
    }

    @FXML
    public void btnAction_SaveValuesGoBackToStackView(ActionEvent event){
        celvcm.saveCardStack();
        screen_checkAlwaysOnTop(Constants.PREF_SV_Boolean_AlwaysOnTop, event, FILE_FXML_StackViewer, bpAll);
    }
}
