package ControllerUI.StackView_Manager;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import assets.Constants;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class StackView_CardManager {


    private FM_CardManager_XML cards;
    private ToastCreator toast;

    VBox vbContainer;
    StackPane spToastMessanger;

    public StackView_CardManager(VBox vbContainer, StackPane spToastMessanger, String stackID){
        this.vbContainer = vbContainer;
        this.spToastMessanger = spToastMessanger;


        // Grab values from xml file
        cards = new FM_CardManager_XML(stackID, false);
        toast = new ToastCreator(spToastMessanger);
        if(cards.getCards().size() > 0){
            createStack(800);
        }
    }

    private VBox createVBoxCreateMainBtn(FM_CardManager_Info card, int buttonSize){

        VBox vb = new VBox();

        // Clicking on vb field sends user to language view
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO ADD HERE TO ADD TO CLIPBOARD
                toast.createToast(card.getCardCopyData());
                writeToClipboard(card.getCardCopyData());
            }
        });

        Label lbTitle = new Label(card.getCardTitle());
        Label lbDescription = new Label(card.getCardDescription());

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
                toast.deleteToast();
            }
        });

        hbButton.getChildren().add(btn);
        hbButton.setAlignment(Pos.TOP_RIGHT);

        // Set sb Styles
        vb.setPrefWidth(buttonSize);
        vb.setStyle("-fx-background-color: white;");
        vb.paddingProperty().setValue(new Insets(10));

        // Add vb
        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbDescription);
        vb.getChildren().add(lbComment);
        vb.getChildren().add(hbButton);

        return vb;
    }

    private void createStack(double windowSize){
        // Column Creator class will generate resizable columns
        int buttonSize = 400; // px
        ColumnCreator cc = new ColumnCreator(vbContainer, buttonSize);
        cc.addListener();

        // Add columns to Column Creator
        ArrayList<VBox> vbCol = new ArrayList<>();
        for(int i = 0; i < cards.getCards().size(); i++){
            vbCol.add(createVBoxCreateMainBtn(cards.getCards().get(i), buttonSize));
        }
        cc.addVBoxArrayContainersToArray(vbCol);

        cc.createColumns(windowSize);
    }


    private void writeToClipboard(String comment){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(comment);
        clipboard.setContent(content);
    }
}
