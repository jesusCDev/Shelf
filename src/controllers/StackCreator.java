package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.SnackBar;
import ControllerUI.DefaultMethods.UI_Feedback.Toast;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_XML;
import FileHandler.FM_StackManager_Info;
import assets.Constants;
import assets.Constants_Prefs;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class StackCreator extends Common_ControllerMethods implements Constants_Prefs{

    @FXML
    Label lbStackTitle;
    @FXML
    TextField tfStackTitle;
    @FXML
    TextArea taStackDescription;
    @FXML
    Button btnCreateStack;
    @FXML
    BorderPane bpContainer_All;

    private Boolean StackGettingEdited;
    private String stackID;
    private FM_StackManager_XML mainStackXmlParser;
    private Toast toast;

    /**
     * Sets default values
     * Sets Up Toast
     * Sets Values to fields if a card is being edited
     */
    @FXML
    public void initialize(){
        screen_SetSize(bpContainer_All);

        StackGettingEdited = pref.getBoolean(PREF_SV_Boolean_Editing, false);
        mainStackXmlParser= new FM_StackManager_XML(false);
        toast = new Toast();

        if(StackGettingEdited){
            btnCreateStack.setText(Constants.TEXT_Update);
            pref.putBoolean(PREF_SV_Boolean_Editing, false);
            FM_StackManager_Info stack = mainStackXmlParser.getStack(pref.get(PREF_SV_String_SelectedStack, ""));

            lbStackTitle.setText(stack.getStackTitle());
            tfStackTitle.setText(stack.getStackTitle());
            taStackDescription.setText(stack.getStackDescription());
            stackID = stack.getStackID();
        }
    }

    /********** Onscreen Button Actions **********/

    /**
     * Creates Stack
     * Updates Stack
     */
    @FXML
    public void btnActionCreateStack(ActionEvent e){

        if(!tfStackTitle.getText().isEmpty() && !StackGettingEdited){
            FM_StackManager_Info stack = new FM_StackManager_Info(tfStackTitle.getText(), taStackDescription.getText(), Boolean.toString(false), mainStackXmlParser.idCreator(16));

            mainStackXmlParser.getStacks().add(stack);
            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();

            FM_CardManager_XML cardFile = new FM_CardManager_XML(stack.getStackID(), true);
            cardFile.createXMLFile();

            changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_Main, e, Constants.WINDOW_TITLE_Main, bpContainer_All, false);
        }else if(!tfStackTitle.getText().isEmpty() && StackGettingEdited){
            mainStackXmlParser.getStack(stackID).setStackTitle(tfStackTitle.getText());
            mainStackXmlParser.getStack(stackID).setStackDescrption(taStackDescription.getText());

            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();

            changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_Main, e, Constants.WINDOW_TITLE_Main, bpContainer_All, false);
        }else{
            toast.showMessage(Constants.TEXT_FillTitle, Constants.WINDOW_TITLE_Error);
        }
    }

    /**
     * Returns user to Main Screen
     * @param e
     */
    @FXML
    public void btnActionCancel(ActionEvent e){
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_Main, e, Constants.WINDOW_TITLE_Main,bpContainer_All, false);
    }
}
