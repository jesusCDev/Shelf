package ControllerUI.DefaultMethods;

import assets.Constants;

public class Common_DefaultMethods implements Constants {

    public void resetValues(){
        pref.put(PREF_SV_String_SelectedStack, "");
        pref.putBoolean(PREF_SV_Boolean_Editing, false);
    }

    public void hardRest(){
        resetPrefValues(PREF_SV_String_MainPath, "");
        resetPrefValues(PREF_SV_String_StackViewList, "");
        resetPrefValues(PREF_SV_String_SelectedStack, "");
        resetPrefValues(PREF_SV_String_SelectedCardPosition, "");
        resetBooleanPrefValue(PREF_SV_Boolean_FirstTimeUsingApp, true);
        resetBooleanPrefValue(PREF_SV_Boolean_Editing, false);
    }

    private void resetPrefValues(String prefID, String value){
        pref.put(prefID, value);
    }

    private void resetBooleanPrefValue(String prefID, boolean value){
        pref.putBoolean(prefID, value);
    }
}
