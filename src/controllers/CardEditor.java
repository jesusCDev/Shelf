package controllers;

import ControllerUI.CardEditor_ListViewCardManager;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class CardEditor extends Common_ControllerMethods{

    @FXML
    BorderPane bpAll;
    @FXML
    StackPane spToast;
    @FXML
    ListView lvCardContainer;

    private CardEditor_ListViewCardManager celvcm;

    public void initialize(){
        screen_SetSize(bpAll);
        celvcm = new CardEditor_ListViewCardManager(Constants.pref.get(Constants.PREF_SV_SelectedStack, null), lvCardContainer, bpAll);
        celvcm.createList();
    }

    @FXML
    public void btnAction_GoBackToStackView(ActionEvent e){
        celvcm.undoChanges();
        screen_changeDynamicAlwaysOnTop(e, Constants.FILE_FXML_StackViewer, bpAll);
    }

    @FXML
    public void btnAction_SaveValuesGoBackToStackView(ActionEvent e){
        celvcm.saveCardStack();
        screen_changeDynamicAlwaysOnTop(e, Constants.FILE_FXML_StackViewer, bpAll);
    }
}
