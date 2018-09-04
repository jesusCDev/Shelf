package ControllerUI.FirstTimeSetUp;

public class DefaultCard {
    private String title;
    private String  code;
    private String description;

    public DefaultCard(String title, String code, String description){
        this.title = title;
        this.code = code;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }
}
