package assets;

import ControllerUI.DefaultCard;
import ControllerUI.DefaultStackContainer;

public interface DefaultStackValues {

    // JAVA
    String commentBlock_Title_1_Java = "Title Comment One";
    String commentBlock_Code_1_Java =
            "/********************************************************************************\n" +
                    "////\n" +
                    "//// TITLE\n" +
                    "////\n" +
                    "********************************************************************************/";
    String commentBlock_Description_1_Java = "Separates Code In Sections Of What They are Affecting";

    String commentBlock_Title_2_Java = "Title Comment Two";
    String commentBlock_Code_2_Java =
            "/****************************************\n" +
                    "/**** SubTitle\n" +
                    "****************************************/";
    String commentBlock_Description_2_Java = "Titles Small Sections in Section";

    String commentBlock_Title_3_Java = "Title Comment Three";
    String commentBlock_Code_3_Java =
            "/********** SubSubTitle **********/";
    String commentBlock_Description_3_Java = "Titles Small Sections in Section";

    DefaultCard[] commentBlockJava_Cards = {new DefaultCard(commentBlock_Title_1_Java, commentBlock_Code_1_Java, commentBlock_Description_1_Java),
            new DefaultCard(commentBlock_Title_2_Java, commentBlock_Code_2_Java, commentBlock_Description_2_Java),
            new DefaultCard(commentBlock_Title_3_Java, commentBlock_Code_3_Java, commentBlock_Description_3_Java)};

    DefaultStackContainer commentBlockJava = new DefaultStackContainer(commentBlockJava_Cards);

    // Java Debugging
    String debuggingBlock_Title_1_Java = "Print Strings";
    String debuggingBlock_Code_1_Java =
            "public void pop(String Message){ System.out.println(message); }";
    String debuggingBlock_Description_1_Java = "Quick method to print out messages for quick debugging.";
    String debuggingBlock_Title_2_Java = "Print Array";
    String debuggingBlock_Code_2_Java =
            "public ovid popA(String[] array){" +
                    "for(String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    String debuggingBlock_Description_2_Java = "Quick method to print out values for array.";
    String debuggingBlock_Title_3_Java = "Print ArrayList";
    String debuggingBlock_Code_3_Java =
            "public ovid popA(ArrayList<String> array){" +
                    "for(String value: array){" +
                    "System.out.println(value);" +
                    "}}";
    String debuggingBlock_Description_3_Java = "Quick method to print out values for array.";

    DefaultCard[] debuggingBlockJava_Cards = {new DefaultCard(debuggingBlock_Title_1_Java, debuggingBlock_Code_1_Java, debuggingBlock_Description_1_Java),
            new DefaultCard(debuggingBlock_Title_2_Java, debuggingBlock_Code_2_Java, debuggingBlock_Description_2_Java),
            new DefaultCard(debuggingBlock_Title_3_Java, debuggingBlock_Code_3_Java, debuggingBlock_Description_3_Java)};

    DefaultStackContainer debuggingBLockJava = new DefaultStackContainer(debuggingBlockJava_Cards);

    // HTML - Templates?

    // CSS

    // JAVASCRIPT

    // PYTHON
}
