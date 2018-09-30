package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.Toast;
import ControllerUI.Managers.Home_StackManager;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * Overview of users created cards
 */
public class Home extends Common_ControllerMethods implements Constants_Prefs{

    @FXML
    BorderPane bpContainer_All;
    @FXML
    VBox vbContainer_Fav;
    @FXML
    VBox vbContainer_Main;

    private FM_StackManager_XML stacks;
    private Home_StackManager sm;
    private int tracker = 1;

    @FXML
    public void initialize(){
        screen_SetSize(bpContainer_All);

        stacks = new FM_StackManager_XML(false);
        sm = new Home_StackManager(bpContainer_All, vbContainer_Fav, vbContainer_Main, stacks);

        // Create Cols
        sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false, Constants.defaultWindowWidth);
        sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false, Constants.defaultWindowWidth);
    }


    /********** Onscreen Button Actions **********/

    /**
     * Recreates card view depending on their state
     * @param e
     */
    @FXML
    public void btnActionEditStacks(ActionEvent e){
        if(tracker == 1){
            sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), true, vbContainer_Fav.getWidth());
            sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), true, vbContainer_Main.getWidth());
        }else{
            sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false, vbContainer_Fav.getWidth());
            sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false, vbContainer_Main.getWidth());
        }
        tracker *= -1;
    }

    /**
     * Forwards User To Create Stack View
     * @param e
     */
    @FXML
    public void btnActionCreateStack(ActionEvent e){
        pref.put(PREF_SV_String_StackViewList, "");
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_StackCreator, e, Constants.WINDOW_TITLE_StackCreator, bpContainer_All, false);
    }
}
