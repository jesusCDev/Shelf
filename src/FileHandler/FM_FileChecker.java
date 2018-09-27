package FileHandler;

import assets.Constants;
import org.jdom2.input.JDOMParseException;

import java.io.File;
import java.util.ArrayList;

public class FM_FileChecker {

    public boolean checkIfMainDocumentExistOrHasBeenMessWith() {
        if (new File(Constants.pref.get(Constants.PREF_SV_String_MainPath, null)).exists()) {
            try {
                FM_StackManager_XML fsm = new FM_StackManager_XML(false);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public void checkIfStacksExistOrHaveBeenMessedWith(){
        FM_StackManager_XML fsm = new FM_StackManager_XML(false);
        for(FM_StackManager_Info stack: fsm.getStacks()){
            if(!checkStack(stack.getStackID())){
                fsm.getStacks().remove(stack);
            }
        }
        fsm.updateXMLFile();
        deleteAllOtherFiles(fsm.getStacks());
    }

    private boolean checkStack(String stackID){
        File file = new File(Constants.pref.get(Constants.PREF_SV_String_MainPath, "") + stackID + ".xml");
        if(file.exists()){
            try{
                FM_CardManager_XML fcm = new FM_CardManager_XML(stackID, false);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    private void deleteAllOtherFiles(ArrayList<FM_StackManager_Info> fsm){
        File mainDir = new File(Constants.pref.get(Constants.PREF_SV_String_MainPath, ""));
        for(File file: mainDir.listFiles()){
            if(!checkWithOfficalPaths(fsm, file.getAbsolutePath().toString())){
                file.delete();
            }
        }
    }

    private Boolean checkWithOfficalPaths(ArrayList<FM_StackManager_Info> fsm, String filePath){
        if(filePath.equalsIgnoreCase(Constants.pref.get(Constants.PREF_SV_String_MainPath, "") + Constants.DOC_Stack_XML)){
            return true;
        }
        for(FM_StackManager_Info stack: fsm){
            if(filePath.equalsIgnoreCase(Constants.pref.get(Constants.PREF_SV_String_MainPath, "") + stack.getStackID() + ".xml")){
                return true;
            }
        }
        return false;
    }
}