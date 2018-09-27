package ControllerUI.Managers;

import ControllerUI.ColumnCreator;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.*;

import static assets.Constants.PREF_SV_String_StackViewList;
import static assets.Constants.pref;

public class StackSelector_StackManager {

    private VBox vb_FavStacksContainer;
    private VBox vb_NonFavStacksContainer;
    private static int stackViewSize = Constants.stackSize;

    private Stack<FM_StackManager_Info> selectedStackIDs = new Stack<>();
    private FM_StackManager_XML stacks;

    private ColumnCreator ccFav;
    private ColumnCreator ccNonFav;

    public StackSelector_StackManager(){
        stacks = new FM_StackManager_XML(false);
        setSelectedStacks();
    }

    private void setSelectedStacks(){
        for(String selectedStackID: pref.get(PREF_SV_String_StackViewList, null).split(",")){
            stacks.getStack(selectedStackID).setSelected(true);
            selectedStackIDs.push(stacks.getStack(selectedStackID));
        }
            resetStackOrders();
    }

    public void setFavVBoxContainer(VBox vb_FavStacksContainer){
        this.vb_FavStacksContainer = vb_FavStacksContainer;
    }
    public void setNonFavVBoxContainer(VBox vb_NonFavStacksContainer){
        this.vb_NonFavStacksContainer = vb_NonFavStacksContainer;
    }

    public void saveSelectedStacks(){
        StringBuilder sb = new StringBuilder();

        for(FM_StackManager_Info value: selectedStackIDs){
            sb.append(value.getStackID());
            sb.append(",");
        }
        pref.put(PREF_SV_String_StackViewList, sb.toString());
    }

    private void setBtnAction_UnSelectCard(String stackID){

        selectedStackIDs.remove(stacks.getStack(stackID));
        resetStackOrders();

        stacks.getStack(stackID).setSelected(false);
        createCols(vb_FavStacksContainer.getWidth());
    }

    private void resetStackOrders(){
        int i = 1;
        for(FM_StackManager_Info stack: selectedStackIDs){
            stack.setSelectedOrder(i);
            i++;
        }
    }

    private void setBtnAction_SelectCard(String stackID){
        stacks.getStack(stackID).setSelected(true);

        selectedStackIDs.push(stacks.getStack(stackID));
        resetStackOrders();

//        stacks.getStack(stackID).setSelectedOrder(selectedStackIDs.size());

        createCols(vb_FavStacksContainer.getWidth());
    }

    public void createCols(double windowSize){
        createStackView(vb_FavStacksContainer, stacks.getFavStacks(), stacks.getFavStacks().size(), ccFav, windowSize);
        createStackView(vb_NonFavStacksContainer, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), ccNonFav, windowSize);
    }


    private void  createStackView(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, ColumnCreator cc, double windowWSize){

        removeListener(cc);

        vb.getChildren().clear();

        if(numOfStacks != 0){
            cc = new ColumnCreator(vb, stackViewSize);
            vb.getChildren().clear();

            // Add columns to Column Creator
            ArrayList<VBox> vbCol = new ArrayList<>();

            // Column Creator class will generate resizable columns
            cc.addListener();

            for(FM_StackManager_Info stack: stacks){
                vbCol.add(createStack(stack));
            }

            cc.addVBoxArrayContainersToArray(vbCol);

            // Create Columns
            cc.createColumns(windowWSize);
        }
    }

    private VBox createStack(FM_StackManager_Info stack){

        VBox vbAll = new VBox();
        vbAll.getChildren().clear();
        vbAll.getStyleClass().add("stack");

        StackPane spContainer = new StackPane();

        VBox vbStack = new VBox();
        vbStack.getStyleClass().add("vbContainer");

        Label lbStackTitle = new Label(stack.getStackTitle());
        lbStackTitle.getStyleClass().add("card_title_1");
        vbStack.getChildren().add(lbStackTitle);
        Label lbStackDescription = new Label(stack.getStackDescription());
        lbStackDescription.getStyleClass().add("card_title_2");
        vbStack.getChildren().add(lbStackDescription);
        vbStack.setFillWidth(true);

        spContainer.getChildren().add(vbStack);
        StackPane.setAlignment(vbStack, Pos.CENTER);

        if (stack.isSelected()){
            spContainer.getChildren().add(setSelected(vbAll, stack));
            if((stack.getSelectedOrder() != 1) || (selectedStackIDs.size() > 1)){
                vbStack.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setBtnAction_UnSelectCard(stack.getStackID());
                    }
                });
            }
        }else{
            vbStack.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setBtnAction_SelectCard(stack.getStackID());
                }
            });
        }

        vbStack.setFillWidth(true);
        vbAll.setPrefWidth(stackViewSize);
        vbAll.getChildren().add(spContainer);

        return vbAll;
    }

    private VBox setSelected(VBox vbAll, FM_StackManager_Info stack){
        VBox p = new VBox();
        p.setAlignment(Pos.CENTER);
        p.getStyleClass().add("stackSelected_indication");
        p.getChildren().add(new Label(Integer.toString(stack.getSelectedOrder())));

        p.prefHeight(Pane.USE_COMPUTED_SIZE);
        p.prefWidth(Pane.USE_COMPUTED_SIZE);
        p.setMaxHeight(Pane.USE_PREF_SIZE);
        p.setMaxWidth(Pane.USE_PREF_SIZE);

        StackPane.setAlignment(p, Pos.TOP_RIGHT);
        return p;
    }


    private void removeListener(ColumnCreator cc){
        try{
            cc.removeListener();
        }catch (NullPointerException e){

        }
    }
}
