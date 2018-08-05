package FileHandler;

public class FM_CardManager_Info {


    private String cardTitle = "";
    private String cardDescription = "";
    private String cardCopyData = "";

    public FM_CardManager_Info(){

    }

    public FM_CardManager_Info(String cardTitle, String cardDescription, String cardCopyData){
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
        this.cardCopyData = cardCopyData;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardCopyData() {
        return cardCopyData;
    }

    public void setCardCopyData(String cardCopyData) {
        this.cardCopyData = cardCopyData;
    }
}
