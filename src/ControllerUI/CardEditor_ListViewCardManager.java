package ControllerUI;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import assets.Constants;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CardEditor_ListViewCardManager {

    private ListView lbCardContainer;
    private ArrayList<FM_CardManager_Info> cards;
    private BorderPane bpAll;
    private String stackName;

    private void createCopyFile(String stackName){
        FM_CardManager_XML copyOfFile = new FM_CardManager_XML("Copy", true);
        copyOfFile.createXMLFile();
        copyOfFile.getCards().addAll(new FM_CardManager_XML(stackName, false).getCards());
        copyOfFile.updateXMLFile();
    }

    public void undoChanges(){
        FM_CardManager_XML undoCards = new FM_CardManager_XML(stackName, false);
        for(int i = 0; i < undoCards.getCards().size(); i++){
            undoCards.getCards().remove(i);
        }
        undoCards.getCards().addAll(new FM_CardManager_XML("Copy", false).getCards());
        undoCards.updateXMLFile();
    }

    public CardEditor_ListViewCardManager(String stackName){
        createCopyFile(stackName);
    }

    public CardEditor_ListViewCardManager(String stackName, ListView lbCardContainer, BorderPane bpAll){
        this.lbCardContainer = lbCardContainer;
        this.bpAll = bpAll;
        this.stackName = stackName;
        cards = new ArrayList<>();
        cards.addAll(new FM_CardManager_XML(stackName, false).getCards());
    }

    private void deleteCard(int cardPosition){
        cards.remove(cardPosition);
        createList();
    }

    public void moveCard(int oldPosition, int newPosition){

    }

    public void saveCardStack(){
        FM_CardManager_XML fcmx = new FM_CardManager_XML(stackName, false);
        for(int i = 0; i < fcmx.getCards().size(); i++){
            fcmx.getCards().remove(i);
        }
        fcmx.getCards().addAll(cards);
        fcmx.updateXMLFile();
    }

    private void editCard(MouseEvent e, int cardPosition){
        Constants.pref.putBoolean(Constants.PREF_SV_Editing, true);
        Constants.pref.putInt(Constants.PREF_SV_SelectedCardPosition, cardPosition);

        Common_ControllerMethods ccm = new Common_ControllerMethods();
        ccm.screen_changeDynamicAlwaysOffTop(e, Constants.FILE_FXML_CardCreator, bpAll);
    }

    public void updateStack(){

    }

    public void createList(){

        lbCardContainer.getItems().clear();

        HBox[] cardsList = new HBox[cards.size()];
        int iter = 0;
        for(FM_CardManager_Info card: cards){
            cardsList[iter] = (createCard(card.getCardTitle(), card.getCardDescription(), card.getCardCopyData(), iter));
            iter++;
        }
        lbCardContainer.getItems().addAll(cardsList);
    }

    private HBox createCard(String title, String description, String data, int cardPosition){
        HBox hbContainer = new HBox();
        hbContainer.getStyleClass().add("card");

        HBox vbButtonContainer = new HBox();
        Button btnEditCard = new Button("Edit");
        btnEditCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                editCard(event, cardPosition);
            }
        });
        Button btnDeleteCard = new Button("Delete");
        btnDeleteCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteCard(cardPosition);
            }
        });
        vbButtonContainer.getChildren().add(btnEditCard);
        vbButtonContainer.getChildren().add(btnDeleteCard);
        vbButtonContainer.setAlignment(Pos.CENTER);

        VBox vbCardInfoContainer = new VBox();
        Label lbTitle = new Label(title);
        lbTitle.getStyleClass().add("card_title_1");
        Label lbDescription = new Label(description);
        lbDescription.getStyleClass().add("card_title_2");
        Label lbData = new Label(data);
        lbData.getStyleClass().add("card_title_3");
        vbCardInfoContainer.getChildren().add(lbTitle);
        vbCardInfoContainer.getChildren().add(lbDescription);
        vbCardInfoContainer.getChildren().add(lbData);


        hbContainer.getChildren().add(vbButtonContainer);
        hbContainer.getChildren().add(vbCardInfoContainer);

        return hbContainer;
    }
}
