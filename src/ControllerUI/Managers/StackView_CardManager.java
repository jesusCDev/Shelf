package ControllerUI.Managers;

import ControllerUI.ColumnCreator;
import ControllerUI.DefaultMethods.Common_ControllerMethods;
import ControllerUI.DefaultMethods.ToastCreator;
import FileHandler.FM_CardManager_Info;
import FileHandler.FM_CardManager_XML;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Stack;

public class StackView_CardManager {


    private FM_CardManager_XML cards;
    private VBox vbAll;
    private ToastCreator toast;
    private VBox vbContainer;
    private static int btnSize = Constants.stackSize; // px
    private BorderPane bpAll;
    private int numOfContainers = 0;

    public StackView_CardManager(BorderPane bpAll, VBox vbAll, VBox vbContainer, String stackID, StackPane spToast, int numOfContainers){
        this.numOfContainers = numOfContainers;
        this.bpAll = bpAll;
        toast = new ToastCreator(spToast);
        this.vbAll = vbAll;
        this.vbContainer = vbContainer;

        vbContainer.setSpacing(5);

        // Grab values from xml file
        cards = new FM_CardManager_XML(stackID, false);
        vbContainer.getChildren().add(create_stackTitle(stackID));
        vbContainer.getChildren().add(create_createCardBtn(stackID, btnSize));
        if(cards.getCards().size() > 0){
            // TODO FOR SOME REASON THIS ISN'T BREAKING THE APP
            System.out.println(bpAll.getWidth());
            vbContainer.getChildren().add(createStack(800));
        }
        vbContainer.getChildren().add(create_EditStackBtn(stackID, btnSize));
    }

    private VBox create_stackTitle(String stackID){
        FM_StackManager_XML fsm = new FM_StackManager_XML(false);
        String title =  fsm.getStack(stackID).getStackTitle();
        VBox vb = new VBox();
        Label lb = new Label(title);
        lb.getStyleClass().add("outlabels");
        lb.getStyleClass().add("title_2");
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(lb);
        vb.setStyle("-fx-border-color: transparent transparent white transparent");
        return vb;
    }

    public VBox getContainer(){
        return vbContainer;
    }

    private VBox create_createCardBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("btnDefault");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Constants.pref.put(Constants.PREF_SV_String_SelectedStack, stackID);
                Common_ControllerMethods ccm = new Common_ControllerMethods();
                ccm.screen_changeDynamicAlwaysOffTop(event, Constants.FILE_FXML_CardCreator, bpAll);
            }
        });
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("+"));
        vb.setPrefWidth(btnSize);
        return vb;
    }

    private VBox create_EditStackBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("vbContainer");
        vb.getStyleClass().add("btnDefault");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                new CardEditor_ListViewCardManager(stackID);

                Constants.pref.put(Constants.PREF_SV_String_SelectedStack, stackID);
                Common_ControllerMethods ccm = new Common_ControllerMethods();
                ccm.screen_changeDynamicAlwaysOffTop(event, Constants.FILE_FXML_CardEditor, bpAll);
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
                toast.createShortToast(card.getCardCopyData());
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
        cc.addListenerWithOutSideCotnainerContainers(vbAll, numOfContainers);

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
