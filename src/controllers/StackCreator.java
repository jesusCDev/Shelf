package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_XML;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.Random;

public class StackCreator extends Common_ControllerMethods{

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
    private String StackID;
    private FM_StackManager_XML mainStackXmlParser;

    @FXML
    public void initialize(){

        screen_SetSize(bpContainer_All);

        StackGettingEdited = Constants.pref.getBoolean(Constants.PREF_SV_Editing, false);
        Constants.pref.putBoolean(Constants.PREF_SV_Editing, false);
        mainStackXmlParser= new FM_StackManager_XML(false);

        if(StackGettingEdited){
            StackID = Constants.pref.get(Constants.PREF_SV_SelectedStack, null);
            btnCreateStack.setText("Update");

            lbStackTitle.setText(mainStackXmlParser.getStack(StackID).getStackTitle());
            tfStackTitle.setText(mainStackXmlParser.getStack(StackID).getStackTitle());
            taStackDescription.setText(mainStackXmlParser.getStack(StackID).getStackDescription());
        }
    }

    public void btnActionCreateStack(ActionEvent btn){

        if(!tfStackTitle.getText().isEmpty() && !StackGettingEdited){
//            FM_StackManager_XML StackXmlParser = new FM_StackManager_XML(true);
            FM_StackManager_Info stack = new FM_StackManager_Info(tfStackTitle.getText(), taStackDescription.getText(), Boolean.toString(false), mainStackXmlParser.idCreator(16));
//            StackXmlParser.createXMLFile();
            mainStackXmlParser.getStacks().add(stack);
            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();

            FM_CardManager_XML cardFile = new FM_CardManager_XML(stack.getStackID(), true);
            cardFile.createXMLFile();

            screen_changeDynamic(btn, Constants.FILE_FXML_Main, bpContainer_All);
        }else if(!tfStackTitle.getText().isEmpty() && StackGettingEdited){
            mainStackXmlParser.getStack(StackID).setStackTitle(tfStackTitle.getText());
            mainStackXmlParser.getStack(StackID).setStackDescrption(taStackDescription.getText());

            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();
            screen_changeDynamic(btn, Constants.FILE_FXML_Main, bpContainer_All);
        }else{
            // TODO TELL USER TO ADD AT LEAST A TITLE
        }
    }

    public void btnActionCancel(ActionEvent btn){
        screen_changeDynamic(btn, Constants.FILE_FXML_Main, bpContainer_All);
    }
}
