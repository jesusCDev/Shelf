package ControllerUI;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import assets.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CardEditor_ListViewCardManager {

    private VBox vbContainer;
    private ArrayList<FM_CardManager_Info> cards;
    private BorderPane bpAll;
    private String stackName;
    private ToastCreator toast;

    private void createCopyFile(String stackName){
        FM_CardManager_XML copyOfFile = new FM_CardManager_XML("Copy", true);
        copyOfFile.createXMLFile();
        copyOfFile.getCards().addAll(new FM_CardManager_XML(stackName, false).getCards());
        copyOfFile.updateXMLFile();
    }

    public void undoChanges(){
        FM_CardManager_XML undoCards = new FM_CardManager_XML(stackName, false);
        undoCards.getCards().clear();
        undoCards.getCards().addAll(new FM_CardManager_XML("Copy", false).getCards());
        undoCards.updateXMLFile();
    }

    public CardEditor_ListViewCardManager(String stackName){
        createCopyFile(stackName);
    }

    public CardEditor_ListViewCardManager(String stackName, VBox vbContainer, BorderPane bpAll, StackPane spToast){
        this.vbContainer = vbContainer;
        this.bpAll = bpAll;
        this.stackName = stackName;
        cards = new ArrayList<>();
        toast = new ToastCreator(spToast);
        cards.addAll(new FM_CardManager_XML(stackName, false).getCards());
    }

    private void deleteCard(int cardPosition){
        cards.remove(cardPosition);
        saveCardStack();
        createList();
    }

    public void moveCard(int oldPosition, int newPosition){
        toast.createToast(cards.get(oldPosition).getCardTitle() + " moved to position " + (newPosition + 1));

        FM_CardManager_Info[] newCards = new FM_CardManager_Info[cards.size()];

        newCards[newPosition] = cards.get(oldPosition);


        int pos = 0;
        for(int i = 0; i < newPosition; i++){
            if(pos == oldPosition){
                pos++;
            }
            newCards[i] = cards.get(pos);
            pos++;
        }

        newCards[newPosition] = cards.get(oldPosition);

        for(int i = (newPosition + 1); i < cards.size(); i++){
            if(pos == oldPosition){
                pos++;
            }
            newCards[i] = cards.get(pos);
            pos++;
        }

        cards.clear();
        for(FM_CardManager_Info card: newCards){
            cards.add(card);
        }
        saveCardStack();
        createList();
    }

    public void saveCardStack(){
        FM_CardManager_XML fcmx = new FM_CardManager_XML(stackName, false);
        fcmx.getCards().clear();
        fcmx.getCards().addAll(cards);
        fcmx.updateXMLFile();
    }

    private void editCard(MouseEvent e, int cardPosition){
        Constants.pref.putBoolean(Constants.PREF_SV_Editing, true);
        Constants.pref.putInt(Constants.PREF_SV_SelectedCardPosition, cardPosition);

        Common_ControllerMethods ccm = new Common_ControllerMethods();
        ccm.screen_changeDynamicAlwaysOffTop(e, Constants.FILE_FXML_CardCreator, bpAll);
    }

    private void setDragFucntions(HBox hbCard, int cardPosition){
        hbCard.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hbCard.getStyleClass().add("selectedCard");
            }
        });
        hbCard.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hbCard.getStyleClass().remove("selectedCard");
            }
        });

        hbCard.setOnDragDetected(new EventHandler <MouseEvent>() {
                public void handle(MouseEvent event) {
                    Dragboard db = hbCard.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(Integer.toString(cardPosition));
                    db.setContent(content);

                    event.consume();
                }
            });

        hbCard.setOnDragOver(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != hbCard && event.getDragboard().hasString()) {
                        hbCard.setStyle("-fx-background-color: #322E61");
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                }
            });

        hbCard.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                        hbCard.setStyle("-fx-background-color: white");
                event.consume();
            }
        });

        hbCard.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();

                boolean success = false;

                if (db.hasString()) {
                    moveCard(Integer.parseInt(db.getString()), cardPosition);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        hbCard.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getTransferMode() == TransferMode.MOVE) {

                }

                event.consume();
            }
        });
    }

    public void createList(){

        vbContainer.getChildren().clear();

        HBox[] cardsList = new HBox[cards.size()];
        int iter = 0;
        for(FM_CardManager_Info card: cards){
            cardsList[iter] = (createCard(card.getCardTitle(), card.getCardDescription(), card.getCardCopyData(), iter));
            iter++;
        }
        vbContainer.getChildren().addAll(cardsList);
    }

    private HBox createCard(String title, String description, String data, int cardPosition){
        HBox hbContainer = new HBox();
        setDragFucntions(hbContainer, cardPosition);

        hbContainer.getStyleClass().add("card");
        hbContainer.getStyleClass().add("hbContainer");

        HBox vbButtonContainer = new HBox();
        vbButtonContainer.getStyleClass().add("hbContainer");
        Button btnEditCard = new Button("Edit");
        btnEditCard.getStyleClass().add("btnFav");
        btnEditCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                editCard(event, cardPosition);
            }
        });
        Button btnDeleteCard = new Button("Delete");
        btnDeleteCard.getStyleClass().add("btnFav");
        btnDeleteCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteCard(cardPosition);
            }
        });
        vbButtonContainer.getChildren().add(btnEditCard);
        vbButtonContainer.getChildren().add(btnDeleteCard);
        btnEditCard.setMinWidth(Button.USE_COMPUTED_SIZE);
        btnDeleteCard.setMinWidth(Button.USE_COMPUTED_SIZE);
        vbButtonContainer.setMinWidth(HBox.USE_COMPUTED_SIZE);
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
