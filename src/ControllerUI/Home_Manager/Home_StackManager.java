package ControllerUI.Home_Manager;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Home_StackManager implements Constants {

    private static int StackSize = Constants.stackSize;

    private String[] selectedStacksID;
    private FM_StackManager_XML stacks;
    private VBox vbContainer_Fav;
    private VBox vbContainer_Main;

    private ColumnCreator ccFav;
    private ColumnCreator ccMain;

    public Home_StackManager(VBox vbContainer_Fav, VBox vbContainer_Main, FM_StackManager_XML stacks, String[] selectedStacksID){
        this.vbContainer_Fav = vbContainer_Fav;
        this.vbContainer_Main = vbContainer_Main;
        this.stacks = stacks;
        this.selectedStacksID = selectedStacksID;
    }

    public void recreateColsAndCheckFav(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, boolean editMode, double windowSize){
        removeListener(ccFav);
        if(numOfStacks != 0){
            ccFav = new ColumnCreator(vb, StackSize);
            recreateCols(vb, stacks, editMode, ccFav, windowSize);
        }else{
            vb.getChildren().clear();
        }
    }

    private void removeListener(ColumnCreator cc){
        try{
            cc.removeListener();
        }catch (NullPointerException e){

        }
    }

    public void recreateColsAndCheckMain(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, boolean editMode, double windowSize){
        removeListener(ccMain);
        if(numOfStacks != 0){
            ccMain = new ColumnCreator(vb, StackSize);
            recreateCols(vb, stacks, editMode, ccMain, windowSize);
        }else{
            vb.getChildren().clear();
        }
    }


    private void recreateCols(VBox vb, ArrayList<FM_StackManager_Info> stacks, boolean editMode, ColumnCreator cc, double windowSize){
        vb.getChildren().clear();

        // Add columns to Column Creator
        ArrayList<VBox> vbCol = new ArrayList<>();

        // Column Creator class will generate resizable columns
        cc.addListener();

        for(FM_StackManager_Info stack: stacks){
            vbCol.add(createVBoxCreateMainBtn(stack.getStackTitle(), stack.getStackDescription(), stack.getStackID(), StackSize, editMode));
        }

        cc.addVBoxArrayContainersToArray(vbCol);

        // Create Columns
        cc.createColumns(windowSize);
    }

    /**
     * Creates the Containers that will be shown in the columns in the container
     * @param title
     * @param summary
     * @return
     */
    private VBox createVBoxCreateMainBtn(String title, String summary, String stackId, int buttonSize, boolean editMode){

        VBox vb = new VBox();

        // Clicking on vb field sends user to Stack view
        setVbAction(vb, stackId, editMode);
        // Create title
        Label lbTitle = new Label(title);

        // create summarytitle
        Label lbSummary = new Label(summary);
        // create favorite buttons
        HBox hbButton = new HBox();
        // TODO ADD AN ICON HERE
        // Clicking the favorite btn allows user to add their most used Stack to the top
        Button btn = new Button();
        setBtnAction(btn, stackId, editMode);

        hbButton.getChildren().add(btn);
        hbButton.setAlignment(Pos.TOP_RIGHT);

        // Set sb Styles
        vb.setPrefWidth(buttonSize);
        vb.setStyle("-fx-background-color: white;");
        vb.paddingProperty().setValue(new Insets(10));

        // Add vb
        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbSummary);
        vb.getChildren().add(hbButton);

        return vb;
    }


    private void setBtnAction(Button btn, String stackId, boolean editMode){
        if(!editMode){
            btn.setText("Fav");
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stacks.changeStackFavStats(stackId);
                    stacks.updateXMLFile();
                    recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), editMode, vbContainer_Fav.getWidth());
                    recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), editMode, vbContainer_Main.getWidth());
                }
            });
        }else{
            btn.setText("Delete");
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    stacks.deleteStack(stackId);
                    stacks.updateXMLFile();
                    recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), editMode, vbContainer_Fav.getWidth());
                    recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), editMode, vbContainer_Main.getWidth());
                }
            });
        }
    }

    private void setVbAction(VBox vb, String stackId, boolean editMode){
        Common_ControllerMethods ccm = new Common_ControllerMethods();

        if(!editMode){
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pref.put(PREF_SV_StackViewList,stackId);
                    ccm.screen_changeNormalAlwaysOnTop(event, FILE_FXML_StackViewer);
                }
            });
        }else{
            // TODO THIS METHOD WILL BE FOR EDITING THE MAIN CARD
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pref.putBoolean(PREF_SV_Editing, true);
                    pref.put(PREF_SV_SelectedStack, stackId);
                    ccm.screen_changeNormalAlwaysOnTop(event, FILE_FXML_StackCreator);
                }
            });
        }
    }

    private void pop(String message){
        System.out.println("\n" + message + "\n");
    }
}
