package ControllerUI.FirstTimeSetUp;

import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;

import java.io.File;

public class FirstTimeSetUp {

    public boolean createFolderForProject(String mainFileLocation){
        File f = new File(mainFileLocation);
        return f.mkdir();
    }

    public void setMainPathValue(String mainFileLocation){
        Constants.pref.put(Constants.PREF_SV_String_MainPath, mainFileLocation);
    }

    public void setFirstTimePref(boolean value){
        Constants.pref.putBoolean(Constants.PREF_SV_Boolean_FirstTimeUsingApp, value);
    }

    public String fixFilePath(String filePath){
        return fixDuplicatesPath(fixEndingSymbolOfPath(filePath) + Constants.FOLDER_Shelf) + File.separator;
    }

    private String fixDuplicatesPath(String filePath){

        String newFilePath = filePath;
        String endingExt;
        int iter = 0;


        while(true){
            if(!new File(newFilePath).exists()){
                System.out.println(newFilePath);
                return newFilePath;
            }
            iter++;
            endingExt = "_" + Integer.toString(iter);
            newFilePath = filePath + endingExt;
        }
    }

    private String fixEndingSymbolOfPath(String filePath){

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
        FM_StackManager_XML sm = new FM_StackManager_XML(true);
        sm.createXMLFile();
        sm.updateXMLFile();

        for(DefaultStackContainer stack: DefaultStackValues.stacks){
            createStacksFiles(stack, sm);
        }
    }

    private void createStacksFiles(DefaultStackContainer stack, FM_StackManager_XML sm){
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
