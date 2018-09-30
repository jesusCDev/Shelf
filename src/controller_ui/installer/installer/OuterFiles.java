package controller_ui.installer.installer;

public interface OuterFiles {

    String DIR_MainFolder = "Shelf_Program";

    String DIR_LanguagesFolder = "Languages";

    String[] PROJECT_DIRECTORIES = {DIR_LanguagesFolder};

    String FILE_LanguageManager = "Shelf.xml";
    String[] PROJECT_FILES = {FILE_LanguageManager};

    String File_ProjectInfo = "_language_data.xml";
    String[] PROJECT_MultipleFiles = {File_ProjectInfo + "*"};
}
