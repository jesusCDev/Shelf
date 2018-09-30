package controller_ui.managers;

import controller_ui.ColumnCreator;
import controller_ui.deafult_methods.Common_ControllerMethods;
import file_manager.FM_StackManager_Info;
import file_manager.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Keeps track of stack view
 */
public class Home_StackManager implements Constants_Prefs {

    private static int STACK_SIZE = Constants.STACK_SIZE;
    private FM_StackManager_XML stacks;
    private VBox vbContainer_Fav;
    private VBox vbContainer_Main;
    private BorderPane bpAll;

    private ColumnCreator ccFav;
    private ColumnCreator ccMain;

    public Home_StackManager(BorderPane bpAll, VBox vbContainer_Fav, VBox vbContainer_Main, FM_StackManager_XML stacks){
        this.bpAll = bpAll;
        this.vbContainer_Fav = vbContainer_Fav;
        this.vbContainer_Main = vbContainer_Main;
        this.stacks = stacks;
    }

    /********** Col Methods **********/
    /**
     * Removes Listeners After Cols Have Been Recreated
     * @param cc
     */
    private void removeListener(ColumnCreator cc){
        try{
            cc.removeListener();
        }catch (NullPointerException e){
        }
    }

    /**
     * Recreates Cols For FAv
     * @param vb
     * @param stacks
     * @param numOfStacks
     * @param editMode
     * @param windowSize
     */
    public void recreateColsAndCheckFav(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, boolean editMode, double windowSize){
        removeListener(ccFav);
        if(numOfStacks != 0){
            ccFav = new ColumnCreator(vb, STACK_SIZE);
            recreateCols(vb, stacks, editMode, ccFav, windowSize);
        }else{
            vb.getChildren().clear();
        }
    }


    /**
     * Recreates Col For main
     * @param vb
     * @param stacks
     * @param numOfStacks
     * @param editMode
     * @param windowSize
     */
    public void recreateColsAndCheckMain(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, boolean editMode, double windowSize){
        removeListener(ccMain);
        if(numOfStacks != 0){
            ccMain = new ColumnCreator(vb, STACK_SIZE);
            recreateCols(vb, stacks, editMode, ccMain, windowSize);
        }else{
            vb.getChildren().clear();
        }
    }

    /**
     * Recreates Col
     * @param vb
     * @param stacks
     * @param editMode
     * @param cc
     * @param windowSize
     */
    private void recreateCols(VBox vb, ArrayList<FM_StackManager_Info> stacks, boolean editMode, ColumnCreator cc, double windowSize){
        vb.getChildren().clear();
        ArrayList<VBox> vbCol = new ArrayList<>();

        cc.addListener();
        for(FM_StackManager_Info stack: stacks){
            vbCol.add(createVBoxCreateMainBtn(stack.getStackTitle(), stack.getStackDescription(), stack.getStackID(), STACK_SIZE, editMode));
        }
        cc.addVBoxArrayContainersToArray(vbCol);
        cc.createColumns(windowSize);
    }

    /********** Stack Container **********/
    /**
     * Creates Views for Stacks
     * @param title
     * @param description
     * @return
     */
    private VBox createVBoxCreateMainBtn(String title, String description, String stackId, int buttonSize, boolean editMode){

        VBox vb = new VBox();
        vb.setMaxHeight(400.0);
        vb.getStyleClass().add("stack");
        vb.getStyleClass().add("vbContainer");
        setVbAction(vb, stackId, editMode);

        Label lbTitle = new Label(title);
        lbTitle.getStyleClass().add("card_title_1");

        Label lbDescription = new Label(description);
        lbDescription.getStyleClass().add("card_title_2");
        HBox hbButton = new HBox();
        Button btn = new Button();
        btn.getStyleClass().add("btnFav");
        setBtnAction(btn, stackId, editMode);

        hbButton.getChildren().add(btn);
        hbButton.setAlignment(Pos.TOP_RIGHT);

        vb.setPrefWidth(buttonSize);
        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbDescription);
        vb.getChildren().add(hbButton);

        return vb;
    }

    /**
     * Recreates cols with btn actions depending on action
     * @param btn
     * @param stackId
     * @param editMode
     */
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

    /**
     * Sets stack view action
     * @param vb
     * @param stackId
     * @param editMode
     */
    private void setVbAction(VBox vb, String stackId, boolean editMode){
        Common_ControllerMethods ccm = new Common_ControllerMethods();

        if(!editMode){
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pref.put(PREF_SV_String_StackViewList,stackId);
                    ccm.screen_checkAlwaysOnTop(PREF_SV_Boolean_AlwaysOnTop, event, Constants.FILE_FXML_StackViewer, Constants.WINDOW_TITLE_StackViewer, bpAll);
                }
            });
        }else{
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pref.putBoolean(PREF_SV_Boolean_Editing, true);
                    pref.put(PREF_SV_String_SelectedStack, stackId);
                    ccm.changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_StackCreator, event, Constants.WINDOW_TITLE_StackCreator, bpAll, false);
                }
            });
        }
    }
}
