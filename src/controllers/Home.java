package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Managers.Home_StackManager;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


// TODO SET SO IT IS NOT ALWAYS ON TOP UNLESS IT IS IN CARD VIEW

public class Home extends Common_ControllerMethods implements Constants{

    @FXML
    BorderPane bpContainer_All;
    @FXML
    VBox vbContainer_Fav;
    @FXML
    VBox vbContainer_Main;

    // Grab values from xml file
    private FM_StackManager_XML stacks;
    private int tracker = 1;
    private Home_StackManager sm;

    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);

        // TODO CHECK IF FILES STILL EXIST IN THE FOLDER, IF THEY DONT, THEN DON'T SHOW IT TO THE USER AS AN OPTION
        pref.put(PREF_SV_MainPath, "/home/jesuscdev/Projects-Programming/Stuff/");

        stacks = new FM_StackManager_XML(false);

        // make sure they are solid and unclickable
        sm = new Home_StackManager(vbContainer_Fav, vbContainer_Main, stacks, pref.get(PREF_SV_StackViewList, "").split(","));

        // Create Cols
        sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false, 800);
        sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false, 800);
    }

    // TODO CHANGE THIS LATER ON
    public void addNewStack(ActionEvent e){
        screen_changeNormal(e, FILE_FXML_StackCreator);
    }

    @FXML
    public void btnActionEditStacks(ActionEvent btn){
        if(tracker == 1){
            sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), true, vbContainer_Fav.getWidth());
            sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), true, vbContainer_Main.getWidth());
        }else{
            sm.recreateColsAndCheckFav(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false, vbContainer_Fav.getWidth());
            sm.recreateColsAndCheckMain(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false, vbContainer_Main.getWidth());
        }
        tracker *= -1;
    }

    @FXML
    public void btnActionCreateStack(ActionEvent btn){
        pref.put(PREF_SV_StackViewList, "");
        screen_changeDynamic(btn, FILE_FXML_StackCreator, bpContainer_All);
    }


    public void pop(String message){
        System.out.println("\n" + message + "\n");
    }
}
