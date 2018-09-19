package ControllerUI.Managers;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
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
    private VBox vbAll;
    private ToastCreator toast;
    private VBox vbContainer;
    private static int btnSize = Constants.stackSize; // px

    public StackView_CardManager(VBox vbAll, VBox vbContainer, String stackID, StackPane spToast){
        toast = new ToastCreator(spToast);
        this.vbAll = vbAll;
        this.vbContainer = vbContainer;

        // Grab values from xml file
        cards = new FM_CardManager_XML(stackID, false);
        vbContainer.getChildren().add(create_createCardBtn(stackID, btnSize));
        if(cards.getCards().size() > 0){
            vbContainer.getChildren().add(createStack(800));
        }
        vbContainer.getChildren().add(create_EditStackBtn(stackID, btnSize));
    }

    public VBox getContainer(){
        return vbContainer;
    }

    private VBox create_createCardBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("btnAddCard");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Constants.pref.put(Constants.PREF_SV_SelectedStack, stackID);
                Common_ControllerMethods ccm = new Common_ControllerMethods();
                ccm.screen_changeNormal(event, Constants.FILE_FXML_CardCreator);
            }
        });
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("+"));
        vb.setPrefWidth(btnSize);
        return vb;
    }

    private VBox create_EditStackBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("btnEditCardStack");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Should i add a new screen or somehting here?");
            }
        });

        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("Edit"));
        vb.setPrefWidth(btnSize);
        return vb;
    }

    private VBox createVBoxCreateMainBtn(FM_CardManager_Info card, int buttonSize){

        VBox vb = new VBox();
        vb.getStyleClass().add("card");

        // Clicking on vb field sends user to language view
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO ADD HERE TO ADD TO CLIPBOARD
                toast.deleteToast();
                toast.createToast(card.getCardCopyData());
                writeToClipboard(card.getCardCopyData());
            }
        });

        Label lbTitle = new Label(card.getCardTitle());
        lbTitle.getStyleClass().add("card_title_1");
        Label lbDescription = new Label(card.getCardDescription());
        lbDescription.getStyleClass().add("card_title_2");

        // Create Data
        Label lbData = new Label(card.getCardCopyData());
        lbData.getStyleClass().add("card_title_3");
        // Set sb Styles
        vb.setPrefWidth(buttonSize);

        // Add vb
        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbDescription);
        vb.getChildren().add(lbData);

        return vb;
    }

    private VBox createStack(double windowSize){
        // Column Creator class will generate resizable columns
        ColumnCreator cc = new ColumnCreator(new VBox(), btnSize);
        cc.addListenerWithOutSideCotnainer(vbAll);

        // Add columns to Column Creator
        ArrayList<VBox> vbCol = new ArrayList<>();
        for(int i = 0; i < cards.getCards().size(); i++){
            vbCol.add(createVBoxCreateMainBtn(cards.getCards().get(i), btnSize));
        }
        cc.addVBoxArrayContainersToArray(vbCol);

        cc.createColumns(windowSize);

        return cc.getContainer();
    }


    private void writeToClipboard(String data){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(data);
        clipboard.setContent(content);
    }
}
