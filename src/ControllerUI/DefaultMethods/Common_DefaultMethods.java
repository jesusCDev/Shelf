package ControllerUI.DefaultMethods;

import assets.Constants;
import assets.Constants_Prefs;

/**
 * Resets Project Data To Default
 */
public class Common_DefaultMethods implements Constants_Prefs {

    /**
     * Resets values at the end of every session
     */
    public void resetValues(){
        pref.put(PREF_SV_String_SelectedStack, "");
        pref.putBoolean(PREF_SV_Boolean_Editing, false);
        pref.putInt(PREF_SV_ScreenWidth, Constants.defaultWindowWidth);
        pref.putInt(PREF_SV_ScreenHeight, Constants.defaultWindowHeight);
    }

    /**
     * Resets values if app is hard reset
     */
    public void hardRest(){
        resetPrefValues(PREF_SV_String_MainPath, "");
        resetPrefValues(PREF_SV_String_StackViewList, "");
        resetPrefValues(PREF_SV_String_SelectedStack, "");
        resetPrefValues(PREF_SV_String_SelectedCardPosition, "");
        resetBooleanPrefValue(PREF_SV_Boolean_FirstTimeUsingApp, true);
        resetBooleanPrefValue(PREF_SV_Boolean_Editing, false);
    }

    /**
     * Resets pref value
     * @param prefID - pref id
     * @param value - pref value
     */
    private void resetPrefValues(String prefID, String value){
        pref.put(prefID, value);
    }

    /**
     * Resets pref value
     * @param prefID - pref id
     * @param value - pref boolean value
     */
    private void resetBooleanPrefValue(String prefID, boolean value){
        pref.putBoolean(prefID, value);
    }
}
