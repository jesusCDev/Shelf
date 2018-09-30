package controller_ui.managers;

import controller_ui.ColumnCreator;
import controller_ui.deafult_methods.Common_ControllerMethods;
import controller_ui.deafult_methods.ui_feedback.SnackBar;
import file_manager.FM_CardManager_Info;
import file_manager.FM_CardManager_XML;
import file_manager.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
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

/**
 * Manges Cards In Stack Views
 */
public class StackView_CardManager implements Constants_Prefs{

    private FM_CardManager_XML cards;
    private VBox vbAll;
    private SnackBar sb;
    private VBox vbContainer;
    private static int btnSize = Constants.STACK_SIZE;
    private BorderPane bpAll;
    private int numOfContainers = 0;

    public StackView_CardManager(BorderPane bpAll, VBox vbAll, VBox vbContainer, String stackID, StackPane spToast, int numOfContainers){
        this.numOfContainers = numOfContainers;
        this.bpAll = bpAll;
        sb = new SnackBar(spToast);
        this.vbAll = vbAll;
        this.vbContainer = vbContainer;

        vbContainer.setSpacing(5);

        cards = new FM_CardManager_XML(stackID, false);
        vbContainer.getChildren().add(create_stackTitle(stackID));
        vbContainer.getChildren().add(create_createCardBtn(stackID, btnSize));
        if(cards.getCards().size() > 0){
            vbContainer.getChildren().add(createStack(Constants.defaultWindowWidth));
        }
        vbContainer.getChildren().add(create_EditStackBtn(stackID, btnSize));
    }

    public VBox getContainer(){
        return vbContainer;
    }

    /**
     * Saves Data Value To Clipboard
     * @param data
     */
    private void writeToClipboard(String data){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(data);
        clipboard.setContent(content);
    }

    /********** Stack Views  **********/
    /**
     * Creates STack Title
     * @param stackID
     * @return
     */
    private VBox create_stackTitle(String stackID){
        FM_StackManager_XML fsm = new FM_StackManager_XML(false);
        String title =  fsm.getStack(stackID).getStackTitle();
        VBox vb = new VBox();
        Label lb = new Label(title);
        lb.getStyleClass().add("labels_OnBackground");
        lb.getStyleClass().add("title_2");
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(lb);
        vb.setStyle("-fx-border-color: transparent transparent white transparent");
        return vb;
    }

    /**
     * Creates Cards
     * @param stackID
     * @param btnSize
     * @return
     */
    private VBox create_createCardBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("btnDefault");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pref.put(PREF_SV_String_SelectedStack, stackID);
                Common_ControllerMethods ccm = new Common_ControllerMethods();
                ccm.changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, Constants.FILE_FXML_CardCreator, event, Constants.WINDOW_TITLE_CardCreator, bpAll, false);
            }
        });
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("+"));
        vb.setPrefWidth(btnSize);
        return vb;
    }

    /**
     * Creates Edit Btn - Edit Current Stack
     * @param stackID
     * @param btnSize
     * @return
     */
    private VBox create_EditStackBtn(String stackID, double btnSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("vbContainer");
        vb.getStyleClass().add("btnDefault");
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new CardEditor_ListViewCardManager(stackID);

                pref.put(PREF_SV_String_SelectedStack, stackID);
                Common_ControllerMethods ccm = new Common_ControllerMethods();
                ccm.changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, Constants.FILE_FXML_CardEditor, event, Constants.WINDOW_TITLE_CardEditor,bpAll, false);
            }
        });

        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("Edit"));
        vb.setPrefWidth(btnSize);
        return vb;
    }

    /**
     * Creates Add Btn - Add Card To stack
     * @param card
     * @param buttonSize
     * @return
     */
    private VBox createCard(FM_CardManager_Info card, int buttonSize){
        VBox vb = new VBox();
        vb.getStyleClass().add("card");
        vb.setMaxHeight(400.0);
        vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sb.createSnackBar(card.getCardCopyData(), SnackBar.SHORT);
                writeToClipboard(card.getCardCopyData());
            }
        });

        Label lbTitle = new Label(card.getCardTitle());
        lbTitle.getStyleClass().add("card_title_1");
        Label lbDescription = new Label(card.getCardDescription());
        lbDescription.getStyleClass().add("card_title_2");

        Label lbData = new Label(card.getCardCopyData());
        lbData.getStyleClass().add("card_title_3");
        vb.setPrefWidth(buttonSize);

        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbDescription);
        vb.getChildren().add(lbData);

        return vb;
    }

    /********** Stack **********/
    /**
     * Creates StackView
     * @param windowSize
     * @return
     */
    private VBox createStack(double windowSize){
        ColumnCreator cc = new ColumnCreator(new VBox(), btnSize);
        cc.addListenerWithSideBySideContainers(vbAll, numOfContainers);

        ArrayList<VBox> vbCol = new ArrayList<>();
        for(int i = 0; i < cards.getCards().size(); i++){
            vbCol.add(createCard(cards.getCards().get(i), btnSize));
        }

        cc.addVBoxArrayContainersToArray(vbCol);
        cc.createColumns(windowSize);
        return cc.getContainer();
    }

}
