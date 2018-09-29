package assets;

import java.util.prefs.Preferences;

/**
 * Saved Constant Values Used Throughout Project
 */
public interface Constants_Prefs {

    /********** Projects Saved Values **********/
    String PREF_SV_String_MainPath = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_MainPath"; // Path to main folder where app is located at
    String PREF_SV_String_StackViewList = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_HoldsMultipleClassesThatAreOpen"; // Holds the names of the
    String PREF_SV_String_SelectedStack = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_HoldsASelectedClass"; // Keeps the selected stack IDs separated by a comma.
    String PREF_SV_String_SelectedCardPosition = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_HoldsPositionForSelectedClass"; // holds selected card position when you refer to the stack
    String PREF_SV_Boolean_AlwaysOnTop = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_IsTheWindowAlwaysOnTop"; // value to check if this is the first time the user is using the app
    String PREF_SV_Boolean_Editing = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_Editing"; // Keep the id of the the stack or card that is being edited
    String PREF_SV_Boolean_FirstTimeUsingApp = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_FirstTimeUsingApp"; // value to check if this is the first time the user is using the app

    /********** Screen Pref Values **********/
    String PREF_SV_ScreenWidth = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_width";
    String PREF_SV_ScreenHeight = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_height";
    String PREF_SV_ScreenMax = "pref_sv_Shelf_Key-QnAE&4^j%6dMrs[4_Maximized";

    /********** Preference **********/
    Preferences pref = Preferences.userRoot();
}
