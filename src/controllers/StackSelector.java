package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.StackSelector_StackManager;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class StackSelector extends Common_ControllerMethods{
    @FXML
    VBox vb_FavStacksContainer;
    @FXML
    VBox vb_NonFavStacksContainer;
    @FXML
    BorderPane bpContainer_All;

    private StackSelector_StackManager ss;

    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);

        ss = new StackSelector_StackManager();
        ss.setFavVBoxContainer(vb_FavStacksContainer);
        ss.setNonFavVBoxContainer(vb_NonFavStacksContainer);

        ss.createCols(800);
    }

    public void btnAction_Cancel(ActionEvent e){
        screen_changeDynamic(e, Constants.FILE_FXML_StackViewer, bpContainer_All);
    }

    public void btnAction_Done(ActionEvent e){
        ss.saveSelectedStacks();
        screen_changeDynamic(e, Constants.FILE_FXML_StackViewer, bpContainer_All);
    }
}
