package ControllerUI.StackView_Manager;

import ControllerUI.ColumnCreator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class StackView_StackManager {

    public StackView_StackManager(VBox vbAll, StackPane spToast, String[] selectedStackIDs){

        ArrayList<VBox> stackContainers = new ArrayList<>();

        for(String stackID: selectedStackIDs){
            stackContainers.add(new StackView_CardManager(new VBox(), stackID).getContainer());
        }

        createStackCols(vbAll, stackContainers);
    }

    public void createStackCols(VBox vbAll, ArrayList<VBox> stackContainers){
        ColumnCreator cc = new ColumnCreator(vbAll, 400);
        cc.addListener();
        cc.addVBoxArrayContainersToArray(stackContainers);
        cc.createColumns(800);
    }
}
