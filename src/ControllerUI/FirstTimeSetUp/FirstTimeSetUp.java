package ControllerUI.FirstTimeSetUp;

import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;

import java.io.File;

public class FirstTimeSetUp {

    public boolean createStackXMLFile(String mainFileLocation){
        File f = new File(mainFileLocation + Constants.DOC_Stack_XML);
        return f.mkdir();
    }

    public void setMainPathValue(String mainFileLocation){
        Constants.pref.put(Constants.PREF_SV_MainPath, mainFileLocation);
    }

    public void setFirstTimePref(boolean value){
        Constants.pref.putBoolean(Constants.PREF_SV_FirstTimeUsingApp, value);
    }

    public String fixFilePath(String filePath){
        StringBuilder sb = new StringBuilder();
        if(!Character.toString(filePath.charAt(filePath.length() - 1)).equalsIgnoreCase(File.separator)){
            sb.append(filePath);
            sb.append(File.separator);
            return sb.toString();
        }
        sb.append(filePath);
        return sb.toString();
    }

    public void createDefaultStacks(){
        for(DefaultStackContainer stack: DefaultStackValues.stacks){
            createStack(stack);
        }
    }

    private void createStack(DefaultStackContainer stack){
        FM_StackManager_XML sm = new FM_StackManager_XML(true);
        sm.createXMLFile();
        String fileID = sm.idCreator(16);
        sm.getStacks().add(new FM_StackManager_Info(stack.getStackTitle(), stack.getStackDescription(), Boolean.toString(false), fileID));
        sm.updateXMLFile();

        createCardsForStack(fileID, stack.getCards());
    }

    private void createCardsForStack(String fileID, DefaultCard[] cards){
        FM_CardManager_XML cm = new FM_CardManager_XML(fileID, true);
        cm.createXMLFile();
        for(DefaultCard card: cards){
            cm.getCards().add(new FM_CardManager_Info(card.getTitle(), card.getDescription(), card.getCode()));
        }
        cm.updateXMLFile();
    }
}
