package FileHandler;

public class FM_StackManager_Info {

    private String stackTitle = "";
    private String stackDescription = "";
    private String stackFavoriteStatus;
    private String stackID;

    private boolean selected = false;
    private int selectedOrder;

    public FM_StackManager_Info(){}

    public FM_StackManager_Info(String stackTitle, String stackDescription, String stackFavoriteStatus, String stackID){
        this.stackTitle = stackTitle;
        this.stackDescription = stackDescription;
        this.stackFavoriteStatus = stackFavoriteStatus;
        this.stackID = stackID;
    }

    public void setStackTitle(String stackTitle){
        this.stackTitle = stackTitle;
    }
    public void setStackDescrption(String stackDescription){
        this.stackDescription = stackDescription;
    }
    public void setStackFavoriteSTatus(String stackFavoriteStatus){
        this.stackFavoriteStatus = stackFavoriteStatus;
    }
    public void setStackID(String stackID) {
        this.stackID = stackID;
    }

    public String getStackTitle(){
        return stackTitle;
    }
    public String getStackDescription(){
        return stackDescription;
    }
    public String getStackFavoriteStatus(){
        return stackFavoriteStatus;
    }
    public String getStackID() {
        return stackID;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(int selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
}
