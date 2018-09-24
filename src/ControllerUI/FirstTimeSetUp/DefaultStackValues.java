package ControllerUI.FirstTimeSetUp;

public class DefaultStackValues {

    // JAVA
    private static String commentBLock_Title_Java = "Java Comments";
    private static String commentBLock_Description_Java = "Basic Comment Separators";
    private static String commentBlock_Title_1_Java = "Title Comment One";
    private static String commentBlock_Code_1_Java =
            "/********************************************************************************\n" +
                    "////\n" +
                    "//// TITLE\n" +
                    "////\n" +
                    "********************************************************************************/";
    private static String commentBlock_Description_1_Java = "Separates Code In Sections Of What They are Affecting";

    private static String commentBlock_Title_2_Java = "Title Comment Two";
    private static String commentBlock_Code_2_Java =
            "/****************************************\n" +
                    "/**** SubTitle\n" +
                    "****************************************/";
    private static String commentBlock_Description_2_Java = "Titles Small Sections in Section";

    private static String commentBlock_Title_3_Java = "Title Comment Three";
    private static String commentBlock_Code_3_Java =
            "/********** SubSubTitle **********/";
    private static String commentBlock_Description_3_Java = "Titles Small Sections in Section";

    private static DefaultCard[] commentBlockJava_Cards = {new DefaultCard(commentBlock_Title_1_Java, commentBlock_Code_1_Java, commentBlock_Description_1_Java),
            new DefaultCard(commentBlock_Title_2_Java, commentBlock_Code_2_Java, commentBlock_Description_2_Java),
            new DefaultCard(commentBlock_Title_3_Java, commentBlock_Code_3_Java, commentBlock_Description_3_Java)};

    private static DefaultStackContainer commentBlockJava = new DefaultStackContainer(commentBLock_Title_Java, commentBLock_Description_Java, commentBlockJava_Cards);

    // Java Debugging
    private static String commentBLock_Title_JavaDebugging = "Java Debugging";
    private static String commentBLock_Description_JavaDebugging = "Basic Debugging methods";
    private static String debuggingBlock_Title_1_Java = "Print private static Strings";
    private static String debuggingBlock_Code_1_Java =
            "public void pop(private static String Message){ System.out.println(message); }";
    private static String debuggingBlock_Description_1_Java = "Quick method to print out messages for quick debugging.";
    private static String debuggingBlock_Title_2_Java = "Print Array";
    private static String debuggingBlock_Code_2_Java =
            "public ovid popA(private static String[] array){" +
                    "for(private static String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    private static String debuggingBlock_Description_2_Java = "Quick method to print out values for array.";
    private static String debuggingBlock_Title_3_Java = "Print ArrayList";
    private static String debuggingBlock_Code_3_Java =
            "public ovid popA(ArrayList<private static String> array){" +
                    "for(private static String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    private static String debuggingBlock_Description_3_Java = "Quick method to print out values for array.";

    private static DefaultCard[] debuggingBlockJava_Cards = {new DefaultCard(debuggingBlock_Title_1_Java, debuggingBlock_Code_1_Java, debuggingBlock_Description_1_Java),
            new DefaultCard(debuggingBlock_Title_2_Java, debuggingBlock_Code_2_Java, debuggingBlock_Description_2_Java),
            new DefaultCard(debuggingBlock_Title_3_Java, debuggingBlock_Code_3_Java, debuggingBlock_Description_3_Java)};

    private static DefaultStackContainer debuggingbLockJava = new DefaultStackContainer(commentBLock_Title_JavaDebugging, commentBLock_Description_JavaDebugging, debuggingBlockJava_Cards);

    // HTML - Templates?
    private static String commentBLock_Title_HTML = "HTML Comments";
    private static String commentBLock_Description_HTML = "Basic Comment Separators";
    private static String commentBlock_Title_1_HTML = "Title Comment One";
    private static String commentBlock_Code_1_HTML =
            "<!---------------------------------------------------------------------------\n" +
                    "*****************************************************************************\n" +
                    "*\n" +
                    "* TITLE 1\n" +
                    "*\n" +
                    "*****************************************************************************\n" +
                    "----------------------------------------------------------------------------->";
    private static String commentBlock_Description_1_HTML = "Separates Code into its Main Sections";
    private static String commentBlock_Title_2_HTML = "Title Comment Two";
    private static String commentBlock_Code_2_HTML =
            "<!----------------------------------------------------------------------------\n" +
                    "* TITLE 2\n" +
                    "----------------------------------------------------------------------------->";
    private static String commentBlock_Description_2_HTML = "Separates Code into its sections";
    private static String commentBlock_Title_3_HTML = "Title Comment Three";
    private static String commentBlock_Code_3_HTML =
            "<!--------------------------------- TITLE 3 --------------------------------->";
    private static String commentBlock_Description_3_HTML = "Separates Code";

    private static DefaultCard[] commentBlockHTML_Cards = {new DefaultCard(commentBlock_Title_1_HTML, commentBlock_Code_1_HTML, commentBlock_Description_1_HTML),
            new DefaultCard(commentBlock_Title_2_HTML, commentBlock_Code_2_HTML, commentBlock_Description_2_HTML),
            new DefaultCard(commentBlock_Title_3_HTML, commentBlock_Code_3_HTML, commentBlock_Description_3_HTML),};

    private static DefaultStackContainer commentBlockHTML = new DefaultStackContainer(commentBLock_Title_HTML, commentBLock_Description_HTML, commentBlockHTML_Cards);

    // CSS JAVASCRIPT
    private static String commentBLock_Title_JavaScriptCSS = "JavaScript & CSS Comments";
    private static String commentBLock_Description_JavaScriptCSS = "Basic Comment Separators";
    private static String commentBlock_Title_1_JavaScriptCSS = "Title Comment 1";
    private static String commentBlock_Code_1_JavaScriptCSS =
            "/*******************************************************************************\n" +
                    "********************************************************************************\n" +
                    "***\n" +
                    "******************************* TITLE 1*****************************************\n" +
                    "***\n" +
                    "********************************************************************************\n" +
                    "*******************************************************************************/";
    private static String commentBlock_Description_1_JavaScriptCSS = "Splits Code Up Into Main Sections";
    private static String commentBlock_Title_2_JavaScriptCSS = "Title Comment 2";
    private static String commentBlock_Code_2_JavaScriptCSS =
            "/******************************************************************************\n" +
                    "************ TITLE 2 \n" +
                    "*******************************************************************************/";
    private static String commentBlock_Description_2_JavaScriptCSS = "Splits Code Up Into Sections";
    private static String commentBlock_Title_3_JavaScriptCSS = "Title Comment 3";
    private static String commentBlock_Code_3_JavaScriptCSS =
            "/******************************** TITLE 3 *************************************/";
    private static String commentBlock_Description_3_JavaScriptCSS = "Separates Code";

    private static DefaultCard[] commentBlockJavaScriptCSS_Cards = {new DefaultCard(commentBlock_Title_1_JavaScriptCSS, commentBlock_Code_1_JavaScriptCSS, commentBlock_Description_1_JavaScriptCSS),
            new DefaultCard(commentBlock_Title_2_JavaScriptCSS, commentBlock_Code_2_JavaScriptCSS, commentBlock_Description_2_JavaScriptCSS),
            new DefaultCard(commentBlock_Title_3_JavaScriptCSS, commentBlock_Code_3_JavaScriptCSS, commentBlock_Description_3_JavaScriptCSS),};

    private static DefaultStackContainer commentBlockJavaScriptCSS = new DefaultStackContainer(commentBLock_Title_JavaScriptCSS, commentBLock_Description_JavaScriptCSS, commentBlockJavaScriptCSS_Cards);

    // PYTHON
    private static String commentBLock_Title_Python = "Python Comments";
    private static String commentBLock_Description_Python = "Basic Comment Separators";
    private static String commentBlock_Title_1_Python = "Title Comment 1";
    private static String commentBlock_Code_1_Python =
            "#######################################################################################################################\n" +
                    "############\n" +
                    "############ TITLE 1\n" +
                    "############\n" +
                    "########################################################################################################################";
    private static String commentBlock_Description_1_Python = "Separates The Code Into Main Sections";

    private static String commentBlock_Title_2_Python = "Title Comment 2";
    private static String commentBlock_Code_2_Python =
            "########################################################################################################################\n" +
                    "############ TITLE 2\n" +
                    "########################################################################################################################";
    private static String commentBlock_Description_2_Python = "Separates Code Into Sections";


    private static String commentBlock_Title_3_Python = "Title Comment 3";
    private static String commentBlock_Code_3_Python =
            "#################################################### TITLE 3 ###########################################################";
    private static String commentBlock_Description_3_Python = "Separates Code";


    private static DefaultCard[] commentBlockPython_Cards = {new DefaultCard(commentBlock_Title_1_Python, commentBlock_Code_1_Python, commentBlock_Description_1_Python),
            new DefaultCard(commentBlock_Title_2_Python, commentBlock_Code_2_Python, commentBlock_Description_2_Python),
            new DefaultCard(commentBlock_Title_3_Python, commentBlock_Code_3_Python, commentBlock_Description_3_Python),};

    private static DefaultStackContainer commentBlockPython= new DefaultStackContainer(commentBLock_Title_Python, commentBLock_Description_Python, commentBlockPython_Cards);

    private static String loremIpsum_Title = "LoremIpsum Text";
    private static String loremIpsum_Description = "Fake use to fill up content blocks.";

    private static String loremIpsum_Title_Long = "Lorem Ipsum Long Title";
    private static String loremIpsum_Title_Medium = "Lorem Ipsum Medium Title";
    private static String loremIpsum_Title_Short = "Lorem Ipsum Short Title";
    private static String loremIpsum_Description_Long = "Lorem Ipsum Long Description";
    private static String loremIpsum_Description_Medium = "Lorem Ipsum Medium Description";
    private static String loremIpsum_Description_Short = "Lorem Ipsum Short Description";

    // CONTAINS ALL STACKS
    public static DefaultStackContainer[] stacks = {commentBlockHTML, commentBlockJava, commentBlockJavaScriptCSS, commentBlockPython, debuggingbLockJava};

}

