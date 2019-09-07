package file_manager;

import assets.Constants;
import assets.Constants_Prefs;

import java.io.File;
import java.util.ArrayList;

/**
 * File Checker - Checks File Access and Removes Clutter
 */
public class FM_FileChecker implements Constants_Prefs{

    /**
     * Checks main Document if it works
     * @return
     */
    public boolean check_MainDocExistAndWorks() {
        if (new File(pref.get(PREF_SV_String_MainPath, null)).exists()) {
            try {
                new FM_StackManager_XML(false);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if stack exist and work
     */
    public void check_StackExistAndWork(){
        FM_StackManager_XML fsm = new FM_StackManager_XML(false);
        for(FM_StackManager_Info stack: fsm.getStacks()){
            if(!check_Stack(stack.getStackID())){
                fsm.getStacks().remove(stack);
            }
        }
        fsm.updateXMLFile();
        delete_AllOtherFiles(fsm.getStacks());
    }

    /**
     * Checks cards in stack
     * @param stackID
     * @return
     */
    private boolean check_Stack(String stackID){
        File file = new File(pref.get(PREF_SV_String_MainPath, "") + stackID + ".xml");
        if(file.exists()){
            try{
                new FM_CardManager_XML(stackID, false);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    /**
     * Deletes File if project isn't part of project
     * @param fsm
     */
    private void delete_AllOtherFiles(ArrayList<FM_StackManager_Info> fsm){
        File mainDir = new File(pref.get(PREF_SV_String_MainPath, ""));
        try {
            for (File file : mainDir.listFiles()) {
                if (!checkIfFileIsPartOfProject(fsm, file.getAbsolutePath().toString())) {
                    file.delete();
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks File if it is part of proejct
     * @param fsm
     * @param filePath
     * @return
     */
    private Boolean checkIfFileIsPartOfProject(ArrayList<FM_StackManager_Info> fsm, String filePath){
        if(filePath.equalsIgnoreCase(pref.get(PREF_SV_String_MainPath, "") + Constants.DOC_Stack_XML)){
            return true;
        }
        for(FM_StackManager_Info stack: fsm){
            if(filePath.equalsIgnoreCase(pref.get(PREF_SV_String_MainPath, "") + stack.getStackID() + ".xml")){
                return true;
            }
        }
        return false;
    }
}