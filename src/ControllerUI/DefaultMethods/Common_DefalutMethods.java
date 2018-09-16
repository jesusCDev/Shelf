package ControllerUI.DefaultMethods;

import assets.Constants;

public class Common_DefalutMethods implements Constants {

    public void resetValues(){
        pref.put(PREF_SV_SelectedStack, "");
        pref.putBoolean(PREF_SV_Editing, false);
    }
}
