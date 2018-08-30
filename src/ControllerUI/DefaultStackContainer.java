package ControllerUI;

import java.util.ArrayList;

public class DefaultStackContainer {

    private DefaultCard[] stacks;

    public DefaultStackContainer(DefaultCard[] stacks){
        this.stacks = stacks;
    }

    public DefaultCard[] getStacks() {
        return stacks;
    }
}
