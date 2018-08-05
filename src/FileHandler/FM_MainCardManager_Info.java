package FileHandler;

public class FM_MainCardManager_Info {

    private String cardTitle = "";
    private String cardDescription = "";
    private String cardFavoriteStatus;
    private String cardID;

    public FM_MainCardManager_Info(){}

    public FM_MainCardManager_Info(String cardTitle, String cardDescription, String cardFavoriteStatus, String cardID){
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
        this.cardFavoriteStatus = cardFavoriteStatus;
        this.cardID = cardID;
    }

    public void setCardTitle(String cardTitle){
        this.cardTitle = cardTitle;
    }
    public void setCardDescrption(String cardDescription){
        this.cardDescription = cardDescription;
    }
    public void setCardFavoriteSTatus(String cardFavoriteStatus){
        this.cardFavoriteStatus = cardFavoriteStatus;
    }
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardTitle(){
        return cardTitle;
    }
    public String getCardDescription(){
        return cardDescription;
    }
    public String getCardFavoriteStatus(){
        return cardFavoriteStatus;
    }
    public String getCardID() {
        return cardID;
    }

}
