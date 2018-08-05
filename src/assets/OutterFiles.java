package assets;

import java.io.File;

public interface OutterFiles {

    String DIR_MainFolder = "Shelf_Program";

    String DIR_LanguagesFolder = "Languages";

    String[] PROJECT_DIRECTORIES = {DIR_LanguagesFolder};

    String FILE_LanguageManager = "projects_languages.xml";
    String[] PROJECT_FILES = {FILE_LanguageManager};

    String File_ProjectInfo = "_language_data.xml";
    String[] PROJECT_MultipleFiles = {File_ProjectInfo + "*"};
}
