package ControllerUI.Managers;

import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.UI_Feedback.SnackBar;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Manages Card Editing List View Actions
 */
public class CardEditor_ListViewCardManager implements Constants_Prefs{

    private VBox vbContainer;
    private ArrayList<FM_CardManager_Info> cards;
    private BorderPane bpAll;
    private String stackName;
    private SnackBar sb;

    /**
     * Sets up Manager
     */
    public CardEditor_ListViewCardManager(String stackName, VBox vbContainer, BorderPane bpAll, StackPane spToast){
        this.vbContainer = vbContainer;
        this.bpAll = bpAll;
        this.stackName = stackName;
        cards = new ArrayList<>();
        sb = new SnackBar(spToast);
        cards.addAll(new FM_CardManager_XML(stackName, false).getCards());
    }

    /********** Card Editing Methods **********/
    /**
     * Creates Copy To Use Later On
     * @param stackName
     */
    public CardEditor_ListViewCardManager(String stackName){
        createCopyFile(stackName);
    }

    /**
     * Creates copy of selected file to use later in-case of reverting
     * @param stackName
     */
    private void createCopyFile(String stackName){
        FM_CardManager_XML copyOfFile = new FM_CardManager_XML("Copy", true);
        copyOfFile.createXMLFile();
        copyOfFile.getCards().addAll(new FM_CardManager_XML(stackName, false).getCards());
        copyOfFile.updateXMLFile();
    }

    /**
     * Reverts Card File To Copy Of File
     */
    public void undoChanges(){
        FM_CardManager_XML undoCards = new FM_CardManager_XML(stackName, false);
        undoCards.getCards().clear();
        undoCards.getCards().addAll(new FM_CardManager_XML("Copy", false).getCards());
        undoCards.updateXMLFile();
    }

    /**
     * Saves Values For Current Card Edits
     */
    public void saveCardStack(){
        FM_CardManager_XML fcmx = new FM_CardManager_XML(stackName, false);
        fcmx.getCards().clear();
        fcmx.getCards().addAll(cards);
        fcmx.updateXMLFile();
    }

    /********** List Methods **********/
    /**
     * Deletes Selected Card
     * @param cardPosition
     */
    private void deleteCard(int cardPosition){
        cards.remove(cardPosition);
        saveCardStack();
        createList();
    }

    /**
     * Moves Card To New Position In List View
     * @param oldPosition
     * @param newPosition
     */
    private void moveCard(int oldPosition, int newPosition){
        sb.createSnackBar(cards.get(oldPosition).getCardTitle() + " moved to position " + (newPosition + 1), SnackBar.SHORT);

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
    /**
     * Sends User To Card Editor Screen with clicked card
     * @param e
     * @param cardPosition
     */
    private void editCard(MouseEvent e, int cardPosition){
        pref.putBoolean(PREF_SV_Boolean_Editing, true);
        pref.putInt(PREF_SV_String_SelectedCardPosition, cardPosition);

        Common_ControllerMethods ccm = new Common_ControllerMethods();
        ccm.changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Constants.FILE_FXML_CardCreator, e, Constants.WINDOW_TITLE_CardCreator,bpAll, false);
    }

    /**
     * Sets Drag functions and styles
     * @param hbCard
     * @param cardPosition
     */
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
                    System.out.println("Done");
                }
                event.consume();
            }
        });
    }

    /**
     * Creates cards and sets values
     */
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

        HBox hbButtonContainer = new HBox();
        hbButtonContainer.getStyleClass().add("hbContainer");
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

        hbButtonContainer.getChildren().add(btnEditCard);
        hbButtonContainer.getChildren().add(btnDeleteCard);
        btnEditCard.setMinWidth(Button.USE_PREF_SIZE);
        btnDeleteCard.setMinWidth(Button.USE_PREF_SIZE);
        hbButtonContainer.setMinWidth(HBox.USE_PREF_SIZE);
        System.out.println("Comp Size" + HBox.USE_PREF_SIZE);
        hbButtonContainer.setAlignment(Pos.CENTER);

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
        vbCardInfoContainer.setMinWidth(0.0);


        hbContainer.getChildren().add(hbButtonContainer);
        hbContainer.getChildren().add(vbCardInfoContainer);

        return hbContainer;
    }
}
