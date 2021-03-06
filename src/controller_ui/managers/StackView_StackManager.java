package controller_ui.managers;

import controller_ui.ColumnCreator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Manges Stack Views
 */
public class StackView_StackManager {

    public StackView_StackManager(BorderPane bpAll, VBox vbAll, StackPane spToast, String[] selectedStackIDs){
        ArrayList<VBox> stackContainers = new ArrayList<>();
        // Grabs Selected Stack Views
        for(String stackID: selectedStackIDs){
            stackContainers.add(new StackView_CardManager(bpAll, vbAll, new VBox(), stackID, spToast, selectedStackIDs.length).getContainer());
        }
        createStackCols(vbAll, stackContainers);
    }

    /**
     * Creates Stack Cols
     * @param vbAll
     * @param stackContainers
     */
    private void createStackCols(VBox vbAll, ArrayList<VBox> stackContainers){
        ColumnCreator cc = new ColumnCreator(vbAll, 400);
        cc.addListener();
        cc.addVBoxArrayContainersToArray(stackContainers);
        cc.createColumns(800);
    }
}
