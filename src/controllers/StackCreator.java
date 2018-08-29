package controllers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
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

    private Boolean StackGettingEdited;
    private String StackID;
    private FM_StackManager_XML mainStackXmlParser;

    @FXML
    public void initialize(){

        StackGettingEdited = Constants.pref.getBoolean(Constants.PREF_SV_Editing, false);
        Constants.pref.putBoolean(Constants.PREF_SV_Editing, false);

        if(StackGettingEdited){
            StackID = Constants.pref.get(Constants.PREF_SV_StackViewTextFileName, null);
            btnCreateStack.setText("Update");

            mainStackXmlParser= new FM_StackManager_XML(false);
            lbStackTitle.setText(mainStackXmlParser.getStack(StackID).getStackTitle());
            tfStackTitle.setText(mainStackXmlParser.getStack(StackID).getStackTitle());
            taStackDescription.setText(mainStackXmlParser.getStack(StackID).getStackDescription());
        }
    }

    public void pm(String message){
        System.out.println(message);
    }
    public void btnActionCreateStack(ActionEvent btn){

        if(!tfStackTitle.getText().isEmpty() && !StackGettingEdited){
            // TODO When am i creating a new document
            mainStackXmlParser = new FM_StackManager_XML(false);
            pm("One");
            FM_StackManager_Info stack = new FM_StackManager_Info(tfStackTitle.getText(), taStackDescription.getText(), Boolean.toString(false), idCreator(16));
            FM_StackManager_XML StackXmlParser = new FM_StackManager_XML(true);
            StackXmlParser.createXMLFile();
            pm("Two");
            pm(stack.toString());
            pm(mainStackXmlParser.toString());
            mainStackXmlParser.getStacks().add(stack);
            pm("Three");
            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }else if(!tfStackTitle.getText().isEmpty() && StackGettingEdited){
            mainStackXmlParser.getStack(StackID).setStackTitle(tfStackTitle.getText());
            mainStackXmlParser.getStack(StackID).setStackDescrption(taStackDescription.getText());

            mainStackXmlParser.reorganizeStackAlphabetically();
            mainStackXmlParser.updateXMLFile();
            screen_changeNormal(btn, Constants.FILE_FXML_Main);
        }else{
            // TODO TELL USER TO ADD AT LEAST A TITLE
        }
    }

    public void btnActionCancel(ActionEvent btn){
        screen_changeNormal(btn, Constants.FILE_FXML_Main);
    }

    private String idCreator(int length){
        StringBuilder sb = new StringBuilder();
        Random ranNum = new Random();

        for(int i = 0; i < length; i++){
            sb.append(ranNum.nextInt(Integer.MAX_VALUE));
        }
        return sb.toString();
    }
}
