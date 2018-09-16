package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.StackSelector_StackManager;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class StackSelector extends Common_ControllerMethods{
    @FXML
    VBox vb_FavStacksContainer;
    @FXML
    VBox vb_NonFavStacksContainer;

    private StackSelector_StackManager ss;

    @FXML
    public void initialize(){
        ss = new StackSelector_StackManager();
        ss.setFavVBoxContainer(vb_FavStacksContainer);
        ss.setNonFavVBoxContainer(vb_NonFavStacksContainer);

        ss.createCols(800);
    }

    public void btnAction_Cancel(ActionEvent e){
        screen_changeNormal(e, Constants.FILE_FXML_StackViewer);
    }

    public void btnAction_Done(ActionEvent e){
        ss.saveSelectedStacks();
        screen_changeNormal(e, Constants.FILE_FXML_StackViewer);
    }
}
