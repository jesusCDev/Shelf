package controllers;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.Home_Manager.StackManager;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;


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
    private StackManager sm;

    @FXML
    public void initialize(){

        // TODO CHECK IF FILES STILL EXIST IN THE FOLDER, IF THEY DONT, THEN DON'T SHOW IT TO THE USER AS AN OPTION
        pref.put(PREF_SV_MainPath, "/home/jesuscdev/Projects-Programming/Stuff");

        if(pref.getBoolean(PREF_SV_FirstTimeUsingApp, true)){

            // Create Files of already predefined comments
            stacks = new FM_StackManager_XML(true);
            stacks.createXMLFile();

            pref.putBoolean(PREF_SV_FirstTimeUsingApp, false);
        }else{
            stacks = new FM_StackManager_XML(false);
        }

        // make sure they are solid and unclickable
        sm = new StackManager(vbContainer_Fav, vbContainer_Main, stacks, pref.get(PREF_SV_StackViewList, "").split(","));

        // Create Cols
        sm.recreateColsAndCheck(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false);
        sm.recreateColsAndCheck(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false);
    }

    // TODO CHANGE THIS LATER ON
    @FXML
    public void addNewStack(ActionEvent e){
        screen_changeNormal(e, FILE_FXML_StackCreator);
    }

    @FXML
    public void btnActionEditStacks(ActionEvent btn){
        if(tracker == 1){
            sm.recreateColsAndCheck(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), true);
            sm.recreateColsAndCheck(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), true);
        }else{
            sm.recreateColsAndCheck(vbContainer_Fav, stacks.getFavStacks(), stacks.getFavStacks().size(), false);
            sm.recreateColsAndCheck(vbContainer_Main, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), false);
        }
        tracker *= -1;
    }

    @FXML
    public void btnActionCreateStack(ActionEvent btn){
        pref.put(PREF_SV_StackViewList, "");
        screen_changeNormal(btn, FILE_FXML_StackCreator);
    }
}
