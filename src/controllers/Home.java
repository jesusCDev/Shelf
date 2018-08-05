package controllers;

import ControllerUI.ColumnCreator;
import ControllerUI.Common_ControllerMethods;
import FileHandler.FM_MainCardManager_Info;
import FileHandler.FM_MainCardManager_XML;
import assets.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;


public class Home extends Common_ControllerMethods {

    @FXML
    BorderPane bpAll;
    @FXML
    VBox vbFav;
    @FXML
    VBox vbMain;

    // Grab values from xml file
    private FM_MainCardManager_XML mainCards;
    private int tracker = 1;

    @FXML
    public void initialize(){

        Constants.pref.put(Constants.PREF_SV_MainPath, "/home/jesuscdev/Projects-Programming/Stuff");

        if(Constants.pref.getBoolean(Constants.PREF_SV_FirstTimeUsingApp, true)){
            // make sure they complete this before passing in the value
            Constants.pref.putBoolean(Constants.PREF_SV_FirstTimeUsingApp, false);

            // Create Files of already predefined comments
            mainCards = new FM_MainCardManager_XML(true);
            mainCards.createXMLFile();
        }else{
            mainCards = new FM_MainCardManager_XML(false);
        }

        if(mainCards.getFavCards().size() > 0) {
            recreateCols(vbFav, mainCards.getFavCards(), false);
        }else{
            vbFav.getChildren().clear();
        }
        if(mainCards.getNonFavCards().size() > 0) {
            recreateCols(vbMain, mainCards.getNonFavCards(), false);
        }else{
            vbMain.getChildren().clear();
        }
    }

    private void recreateCols(VBox vb, ArrayList<FM_MainCardManager_Info> cards, boolean editMode){
        vb.getChildren().clear();
        // Column Creator class will generate resizable columns
        int buttonSize = 400; // px
        ColumnCreator cc = new ColumnCreator(vb, buttonSize);

        // Add columns to Column Creator
        ArrayList<VBox> vbCol = new ArrayList<>();

        for(FM_MainCardManager_Info card: cards){
            vbCol.add(createVBoxCreateMainBtn(card.getCardTitle(), card.getCardDescription(), card.getCardID(), buttonSize, editMode));
        }
        cc.addVBoxArrayContainersToArray(vbCol);

        // Create Columns
        cc.createColumns();
    }

    /**
     * Creates the Containers that will be shown in the columns in the container
     * @param title
     * @param summary
     * @return
     */
    private VBox createVBoxCreateMainBtn(String title, String summary, String cardId, int buttonSize, boolean editMode){

        VBox vb = new VBox();

        // Clicking on vb field sends user to Card view
        setVbAction(vb, cardId, editMode);
        // Create title
        Label lbTitle = new Label(title);

        // create summarytitle
        Label lbSummary = new Label(summary);
        // create favorite buttons
        HBox hbButton = new HBox();
        // TODO ADD AN ICON HERE
        // Clicking the favorite btn allows user to add their most used Card to the top
        Button btn = new Button();
        setBtnAction(btn, cardId, editMode);

        hbButton.getChildren().add(btn);
        hbButton.setAlignment(Pos.TOP_RIGHT);

        // Set sb Styles
        vb.setPrefWidth(buttonSize);
        vb.setStyle("-fx-background-color: white;");
        vb.paddingProperty().setValue(new Insets(10));

        // Add vb
        vb.getChildren().add(lbTitle);
        vb.getChildren().add(lbSummary);
        vb.getChildren().add(hbButton);

        return vb;
    }

    private void setBtnAction(Button btn, String cardId, boolean editMode){
        if(!editMode){
            btn.setText("Fav");
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mainCards.changeCardFavStats(cardId);
                    mainCards.updateXMLFile();


                    if(mainCards.getFavCards().size() > 0) {
                        recreateCols(vbFav, mainCards.getFavCards(), editMode);
                    }else{
                        vbFav.getChildren().clear();
                    }
                    if(mainCards.getNonFavCards().size() > 0) {
                        recreateCols(vbMain, mainCards.getNonFavCards(), editMode);
                    }else{
                        vbMain.getChildren().clear();
                    }
                }
            });
        }else{
            btn.setText("Delete");

            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // TODO DELTE CARD FROM FILE
                    // TODO DELTE CARD IN FOLDER
                    mainCards.deleteCard(cardId);
                    mainCards.updateXMLFile();


                    if(mainCards.getFavCards().size() > 0) {
                        recreateCols(vbFav, mainCards.getFavCards(), editMode);
                    }else{
                        vbFav.getChildren().clear();
                    }
                    if(mainCards.getNonFavCards().size() > 0) {
                        recreateCols(vbMain, mainCards.getNonFavCards(), editMode);
                    }else{
                        vbMain.getChildren().clear();
                    }
                }
            });
        }
    }

    private void setVbAction(VBox vb, String cardId, boolean editMode){
        if(!editMode){
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Constants.pref.put(Constants.PREF_SV_CardViewTextFileName, cardId);
                    screen_changeNormalAlwaysOnTop(event, Constants.FILE_FXML_CardViewer);
                }
            });
        }else{
            // TODO THIS METHOD WILL BE FOR EDITING THE MAIN CARD
            vb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Constants.pref.putBoolean(Constants.PREF_SV_EditingCard, true);
                    Constants.pref.put(Constants.PREF_SV_CardViewTextFileName, cardId);
                    screen_changeNormalAlwaysOnTop(event, Constants.FILE_FXML_CardCreator);
                }
            });
        }
    }

    @FXML
    public void btnActionEditCards(ActionEvent btn){
        if(tracker == 1){
            if(mainCards.getFavCards().size() > 0) {
                recreateCols(vbFav, mainCards.getFavCards(), true);
            }else{
                vbFav.getChildren().clear();
            }
            if(mainCards.getNonFavCards().size() > 0) {
                recreateCols(vbMain, mainCards.getNonFavCards(), true);
            }else{
                vbMain.getChildren().clear();
            }
        }else{
            if(mainCards.getFavCards().size() > 0) {
                recreateCols(vbFav, mainCards.getFavCards(), false);
            }else{
                vbFav.getChildren().clear();
            }
            if(mainCards.getNonFavCards().size() > 0) {
                recreateCols(vbMain, mainCards.getNonFavCards(), false);
            }else{
                vbMain.getChildren().clear();
            }
        }
        tracker *= -1;
    }

    @FXML
    public void btnActionCreateCard(ActionEvent btn){
        screen_changeNormal(btn, Constants.FILE_FXML_CardCreator);
    }
}
