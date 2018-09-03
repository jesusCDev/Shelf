package ControllerUI;

import java.util.ArrayList;

public class DefaultStackContainer {

    private String stackTitle;
    private String stackDescription;
    private DefaultCard[] cards;

    public DefaultStackContainer(String stackTitle, String stackDescription, DefaultCard[] cards){
        this.stackTitle = stackTitle;
        this.stackDescription = stackDescription;
        this.cards = cards;
    }

    public String getStackTitle(){
        return stackTitle;
    }

    public String getStackDescription(){
        return stackDescription;
    }

    public DefaultCard[] getCards() {
        return cards;
    }
}
