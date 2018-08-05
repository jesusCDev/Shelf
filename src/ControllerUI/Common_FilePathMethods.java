package ControllerUI;

import java.io.File;

public class Common_FilePathMethods {

    /**
     * Checks Path for ending symbol and removes it incase user has place one in it
     * @param path
     * @return
     */
    public String pathFixer_EndingSymbol(String path){
        if(Character.toString(path.charAt(path.length())).equalsIgnoreCase(File.separator)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < (path.length() - 1); i++){
                sb.append(path.charAt(i));
            }
            return sb.toString();
        }else{
            return path;
        }
    }
}
