package controllers;

import ControllerUI.ColumnCreator;
import ControllerUI.Common_ControllerMethods;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_MainCardManager_Info;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static assets.Defaults.COMMENTS_JAVA;

public class CardView extends Common_ControllerMethods {

    @FXML
    VBox vbContainer;
    private FM_CardManager_XML cards;

    public void initialize(){

        // Grab values from xml file
        cards = new FM_CardManager_XML(Constants.pref.get(Constants.PREF_SV_CardViewTextFileName, null), false);

        if(cards.getCards().size() > 0){
            createVBox();
        }
    }

    private void createVBox(){
        // Column Creator class will generate resizable columns
        int buttonSize = 400; // px
        ColumnCreator cc = new ColumnCreator(vbContainer, buttonSize);

        // Add columns to Column Creator
        ArrayList<VBox> vbCol = new ArrayList<>();
        for(int i = 0; i < cards.getCards().size(); i++){
            vbCol.add(createVBoxCreateMainBtn(cards.getCards().get(i), buttonSize));
        }
        cc.addVBoxArrayContainersToArray(vbCol);

        // Create Columns
        cc.createColumns();
    }

    private void writeToClipboard(String comment){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(comment);
        clipboard.setContent(content);
    }

    private VBox createVBoxCreateMainBtn(FM_CardManager_Info card, int buttonSize){

        VBox vb = new VBox();

        // Clicking on vb field sends user to language view
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO ADD HERE TO ADD TO CLIPBOARD
                writeToClipboard(card.getCardCopyData());
            }
        });

        // Create Comment
        Label lbComment = new Label(card.getCardCopyData());

        // create favorite buttons
        HBox hbButton = new HBox();
        // TODO ADD AN ICON HERE
        // Clicking the favorite btn allows user to add their most used language to the top
        Button btn = new Button("=");
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Favorited this");
            }
        });

        hbButton.getChildren().add(btn);
        hbButton.setAlignment(Pos.TOP_RIGHT);

        // Set sb Styles
        vb.setPrefWidth(buttonSize);
        vb.setStyle("-fx-background-color: white;");
        vb.paddingProperty().setValue(new Insets(10));

        // Add vb
        vb.getChildren().add(lbComment);
        vb.getChildren().add(hbButton);

        return vb;
    }
    // BUTTON ACTIONS

    @FXML
    public void btn_GoBackToMainMenu(ActionEvent e){
        screen_changeNormal(e, Constants.FILE_FXML_Main);
    }
}
