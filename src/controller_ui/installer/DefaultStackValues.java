package controller_ui.installer;

import file_manager.FM_CardManager_Info;
import file_manager.FM_StackManager_Info;

public class DefaultStackValues {

    /********** Java Comment Blocks **********/
    // JAVA
    private static String commentBLock_Title_Java = "Java Comments";
    private static String commentBLock_Description_Java = "Java Basic Comment Separators";
    private static String commentBlock_Title_1_Java = "Java Title Comment One";
    private static String commentBlock_Code_1_Java =
            "/********************************************************************************\n" +
                    "////\n" +
                    "//// TITLE\n" +
                    "////\n" +
                    "********************************************************************************/";
    private static String commentBlock_Description_1_Java = "Separates Code In Sections Of What They are Affecting";

    private static String commentBlock_Title_2_Java = "Java Title Comment Two";
    private static String commentBlock_Code_2_Java =
            "/****************************************\n" +
                    "/**** SubTitle\n" +
                    "****************************************/";
    private static String commentBlock_Description_2_Java = "Titles Small Sections in Section";

    private static String commentBlock_Title_3_Java = "Java Title Comment Three";
    private static String commentBlock_Code_3_Java =
            "/********** SubSubTitle **********/";
    private static String commentBlock_Description_3_Java = "Titles Small Sections in Section";

    private static FM_CardManager_Info[] commentBlockJava_Cards = {new FM_CardManager_Info(commentBlock_Title_1_Java, commentBlock_Description_1_Java, commentBlock_Code_1_Java),
            new FM_CardManager_Info(commentBlock_Title_2_Java, commentBlock_Description_2_Java, commentBlock_Code_2_Java),
            new FM_CardManager_Info(commentBlock_Title_3_Java, commentBlock_Description_3_Java, commentBlock_Code_3_Java)};

    private static FM_StackManager_Info commentBlockJava = new FM_StackManager_Info(commentBLock_Title_Java, commentBLock_Description_Java, commentBlockJava_Cards);

    /********** Java Debugging Code Blocks **********/
    // Java Debugging
    private static String commentBLock_Title_JavaDebugging = "Java Debugging";
    private static String commentBLock_Description_JavaDebugging = "Java Basic Debugging methods";
    private static String debuggingBlock_Title_1_Java = "Print private static Strings";
    private static String debuggingBlock_Code_1_Java =
            "public void pop(String Message){ System.out.println(message); }";
    private static String debuggingBlock_Description_1_Java = "Quick method to print out messages for quick debugging.";
    private static String debuggingBlock_Title_2_Java = "Java Print Array";
    private static String debuggingBlock_Code_2_Java =
            "public void popA(private static String[] array){" +
                    "for(private static String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    private static String debuggingBlock_Description_2_Java = "Quick method to print out values for array.";
    private static String debuggingBlock_Title_3_Java = "Java Print ArrayList";
    private static String debuggingBlock_Code_3_Java =
            "public ovid popAL(ArrayList<private static String> array){" +
                    "for(private static String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    private static String debuggingBlock_Description_3_Java = "Quick method to print out values for array.";

    private static FM_CardManager_Info[] debuggingBlockJava_Cards = {new FM_CardManager_Info(debuggingBlock_Title_1_Java, debuggingBlock_Description_1_Java, debuggingBlock_Code_1_Java),
            new FM_CardManager_Info(debuggingBlock_Title_2_Java, debuggingBlock_Description_2_Java, debuggingBlock_Code_2_Java),
            new FM_CardManager_Info(debuggingBlock_Title_3_Java, debuggingBlock_Description_3_Java, debuggingBlock_Code_3_Java)};

    private static FM_StackManager_Info debuggingbLockJava = new FM_StackManager_Info(commentBLock_Title_JavaDebugging, commentBLock_Description_JavaDebugging, debuggingBlockJava_Cards);

    /********** HTML Comment Block **********/
    // HTML - Templates?
    private static String commentBLock_Title_HTML = "HTML Comments";
    private static String commentBLock_Description_HTML = "HTML Basic Comment Separators";
    private static String commentBlock_Title_1_HTML = "Title Comment One";
    private static String commentBlock_Code_1_HTML =
            "<!---------------------------------------------------------------------------\n" +
                    "*****************************************************************************\n" +
                    "*\n" +
                    "* TITLE 1\n" +
                    "*\n" +
                    "*****************************************************************************\n" +
                    "----------------------------------------------------------------------------->";
    private static String commentBlock_Description_1_HTML = "Separates Code into its main Sections";
    private static String commentBlock_Title_2_HTML = "HTML Title Comment Two";
    private static String commentBlock_Code_2_HTML =
            "<!----------------------------------------------------------------------------\n" +
                    "* TITLE 2\n" +
                    "----------------------------------------------------------------------------->";
    private static String commentBlock_Description_2_HTML = "Separates Code into its sections";
    private static String commentBlock_Title_3_HTML = "HTML Title Comment Three";
    private static String commentBlock_Code_3_HTML =
            "<!--------------------------------- TITLE 3 --------------------------------->";
    private static String commentBlock_Description_3_HTML = "Separates Code";

    private static FM_CardManager_Info[] commentBlockHTML_Cards = {new FM_CardManager_Info(commentBlock_Title_1_HTML, commentBlock_Description_1_HTML, commentBlock_Code_1_HTML),
            new FM_CardManager_Info(commentBlock_Title_2_HTML, commentBlock_Description_2_HTML, commentBlock_Code_2_HTML),
            new FM_CardManager_Info(commentBlock_Title_3_HTML, commentBlock_Description_3_HTML, commentBlock_Code_3_HTML),};

    private static FM_StackManager_Info commentBlockHTML = new FM_StackManager_Info(commentBLock_Title_HTML, commentBLock_Description_HTML, commentBlockHTML_Cards);

    /********** JavaScript/CSS Comment Block **********/
    // CSS JAVASCRIPT
    private static String commentBLock_Title_JavaScriptCSS = "JavaScript & CSS Comments";
    private static String commentBLock_Description_JavaScriptCSS = "Basic Comment Separators";
    private static String commentBlock_Title_1_JavaScriptCSS = "JavaScript/CSS Title Comment 1";
    private static String commentBlock_Code_1_JavaScriptCSS =
            "/*******************************************************************************\n" +
                    "********************************************************************************\n" +
                    "***\n" +
                    "******************************* TITLE 1*****************************************\n" +
                    "***\n" +
                    "********************************************************************************\n" +
                    "*******************************************************************************/";
    private static String commentBlock_Description_1_JavaScriptCSS = "Splits Code Up Into main Sections";
    private static String commentBlock_Title_2_JavaScriptCSS = "JavaScript/CSS Title Comment 2";
    private static String commentBlock_Code_2_JavaScriptCSS =
            "/******************************************************************************\n" +
                    "************ TITLE 2 \n" +
                    "*******************************************************************************/";
    private static String commentBlock_Description_2_JavaScriptCSS = "Splits Code Up Into Sections";
    private static String commentBlock_Title_3_JavaScriptCSS = "JavaScript/CSS Title Comment 3";
    private static String commentBlock_Code_3_JavaScriptCSS =
            "/******************************** TITLE 3 *************************************/";
    private static String commentBlock_Description_3_JavaScriptCSS = "Separates Code";

    private static FM_CardManager_Info[] commentBlockJavaScriptCSS_Cards = {new FM_CardManager_Info(commentBlock_Title_1_JavaScriptCSS, commentBlock_Description_1_JavaScriptCSS, commentBlock_Code_1_JavaScriptCSS),
            new FM_CardManager_Info(commentBlock_Title_2_JavaScriptCSS, commentBlock_Description_2_JavaScriptCSS, commentBlock_Code_2_JavaScriptCSS),
            new FM_CardManager_Info(commentBlock_Title_3_JavaScriptCSS, commentBlock_Description_3_JavaScriptCSS, commentBlock_Code_3_JavaScriptCSS),};

    private static FM_StackManager_Info commentBlockJavaScriptCSS = new FM_StackManager_Info(commentBLock_Title_JavaScriptCSS, commentBLock_Description_JavaScriptCSS, commentBlockJavaScriptCSS_Cards);

    /********** Python Comment Block **********/
    // PYTHON
    private static String commentBLock_Title_Python = "Python Comments";
    private static String commentBLock_Description_Python = "Basic Comment Separators";
    private static String commentBlock_Title_1_Python = "Python Title Comment 1";
    private static String commentBlock_Code_1_Python =
            "#######################################################################################################################\n" +
                    "############\n" +
                    "############ TITLE 1\n" +
                    "############\n" +
                    "########################################################################################################################";
    private static String commentBlock_Description_1_Python = "Separates The Code Into main Sections";

    private static String commentBlock_Title_2_Python = "Python Title Comment 2";
    private static String commentBlock_Code_2_Python =
            "########################################################################################################################\n" +
                    "############ TITLE 2\n" +
                    "########################################################################################################################";
    private static String commentBlock_Description_2_Python = "Separates Code Into Sections";


    private static String commentBlock_Title_3_Python = "Python Title Comment 3";
    private static String commentBlock_Code_3_Python =
            "#################################################### TITLE 3 ###########################################################";
    private static String commentBlock_Description_3_Python = "Separates Code";


    private static FM_CardManager_Info[] commentBlockPython_Cards = {new FM_CardManager_Info(commentBlock_Title_1_Python, commentBlock_Description_1_Python, commentBlock_Code_1_Python),
            new FM_CardManager_Info(commentBlock_Title_2_Python, commentBlock_Description_2_Python, commentBlock_Code_2_Python),
            new FM_CardManager_Info(commentBlock_Title_3_Python, commentBlock_Description_3_Python, commentBlock_Code_3_Python),};

    private static FM_StackManager_Info commentBlockPython= new FM_StackManager_Info(commentBLock_Title_Python, commentBLock_Description_Python, commentBlockPython_Cards);

//    /********** LoremIpsum Text **********/
//    private static String loremIpsum_Title = "LoremIpsum Text";
//    private static String loremIpsum_Description = "Fake use to fill up content blocks.";
//
//    private static String loremIpsum_Title_Long = "Lorem Ipsum Long Title";
//    private static String loremIpsum_Title_Medium = "Lorem Ipsum Medium Title";
//    private static String loremIpsum_Title_Short = "Lorem Ipsum Short Title";
//    private static String loremIpsum_Description_Long = "Lorem Ipsum Long Description";
//    private static String loremIpsum_Description_Medium = "Lorem Ipsum Medium Description";
//    private static String loremIpsum_Description_Short = "Lorem Ipsum Short Description";


    /********** Comment Container **********/
    // CONTAINS ALL STACKS
    public static FM_StackManager_Info[] stacks = {commentBlockHTML, commentBlockJava, commentBlockJavaScriptCSS, commentBlockPython, debuggingbLockJava};

}

