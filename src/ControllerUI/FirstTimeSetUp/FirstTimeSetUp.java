package ControllerUI.FirstTimeSetUp;

import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;

import java.io.File;

public class FirstTimeSetUp implements Constants_Prefs{

    /**
     * Creates Main Folder Where Project And Any Files Related To It Will Remain
     * @param mainFileLocation
     * @return
     */
    public boolean createFolderForProject(String mainFileLocation){
        File f = new File(mainFileLocation);
        return f.mkdir();
    }

    public void setMainPathValue(String mainFileLocation){
        pref.put(PREF_SV_String_MainPath, mainFileLocation);
    }

    public void setFirstTimePref(boolean value){
        pref.putBoolean(PREF_SV_Boolean_FirstTimeUsingApp, value);
    }

    /********** Main Path Fixer **********/
    /**
     * Creates Main Path
     * @param filePath
     * @return
     */
    public String fixFilePath(String filePath){
        return fixDuplicatesPath(fixEndingSymbolOfPath(filePath) + Constants.FOLDER_Shelf) + File.separator;
    }

    /**
     * Insures that file name for folder has not been used yet
     * @param filePath
     * @return
     */
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
            endingExt = Constants.SYMBOL_Underscore + Integer.toString(iter);
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

    /********** Create Defaults **********/

    /**
     * Creates default stack values
     */
    public void createDefaultStacks(){
        FM_StackManager_XML sm = new FM_StackManager_XML(true);
        sm.createXMLFile();
        sm.updateXMLFile();

        for(FM_StackManager_Info stack: DefaultStackValues.stacks){
            createStacksFiles(stack, sm);
        }
    }

    private void createStacksFiles(FM_StackManager_Info stack, FM_StackManager_XML sm){
        String fileID = sm.idCreator(16);
        sm.getStacks().add(new FM_StackManager_Info(stack.getStackTitle(), stack.getStackDescription(), Boolean.toString(false), fileID));
        sm.updateXMLFile();

        createCardsForStack(fileID, stack.getCards());
    }

    private void createCardsForStack(String fileID, FM_CardManager_Info[] cards){
        FM_CardManager_XML cm = new FM_CardManager_XML(fileID, true);
        cm.createXMLFile();

        for(FM_CardManager_Info card: cards){
            cm.getCards().add(new FM_CardManager_Info(card.getCardTitle(), card.getCardDescription(), card.getCardCopyData()));
        }

        cm.updateXMLFile();
    }
}
