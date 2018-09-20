package FileHandler;

import assets.Constants;
import org.jdom2.input.JDOMParseException;

import java.io.File;

public class FM_FileChecker {

    public boolean checkIfMainDocumentExistOrHasBeenMessWith() {
        if (new File(Constants.pref.get(Constants.PREF_SV_MainPath, null)).exists()) {
            try {
                FM_StackManager_XML fsm = new FM_StackManager_XML(false);
                return checkIfStacksExistOrHaveBeenMessedWith(fsm);
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    private boolean checkIfStacksExistOrHaveBeenMessedWith(FM_StackManager_XML fsm){

        return false;
    }

    private void deleteStackBecauseItDoesntExistOrWork(){

    }
}