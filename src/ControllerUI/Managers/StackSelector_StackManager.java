package ControllerUI.Managers;

import ControllerUI.ColumnCreator;
import FileHandler.FM_StackManager_Info;
import FileHandler.FM_StackManager_XML;
import assets.Constants;
import assets.Constants_Prefs;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class StackSelector_StackManager implements Constants_Prefs{

    private VBox vb_FavStacksContainer;
    private VBox vb_NonFavStacksContainer;
    private static int stackViewSize = Constants.STACK_SIZE;

    private Stack<FM_StackManager_Info> selectedStackIDs = new Stack<>();
    private FM_StackManager_XML stacks;

    private ColumnCreator ccFav;
    private ColumnCreator ccNonFav;

    public StackSelector_StackManager(){
        stacks = new FM_StackManager_XML(false);
        setSelectedStacks();
    }

    /********** Set Default Methods **********/
    /**
     * Sets Selected Methods
     */
    private void setSelectedStacks(){
        for(String selectedStackID: pref.get(PREF_SV_String_StackViewList, null).split(Constants.SYMBOL_Comma)){
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

    /********** Selected Card Methods **********/
    /**
     * Saves selected stacks
     */
    public void saveSelectedStacks(){
        StringBuilder sb = new StringBuilder();
        for(FM_StackManager_Info value: selectedStackIDs){
            sb.append(value.getStackID());
            sb.append(Constants.SYMBOL_Comma);
        }
        pref.put(PREF_SV_String_StackViewList, sb.toString());
    }

    private void setBtnAction_UnSelectCard(String stackID){

        selectedStackIDs.remove(stacks.getStack(stackID));
        resetStackOrders();

        stacks.getStack(stackID).setSelected(false);
        createCols(vb_FavStacksContainer.getWidth());
    }

    private void setBtnAction_SelectCard(String stackID){
        stacks.getStack(stackID).setSelected(true);

        selectedStackIDs.push(stacks.getStack(stackID));
        resetStackOrders();

        createCols(vb_FavStacksContainer.getWidth());
    }

    private void resetStackOrders(){
        int i = 1;
        for(FM_StackManager_Info stack: selectedStackIDs){
            stack.setSelectedOrder(i);
            i++;
        }
    }

    /********** View Methods **********/
    /**
     * Creates Col for Containers
     * @param windowSize
     */
    public void createCols(double windowSize){
        createStackView(vb_FavStacksContainer, stacks.getFavStacks(), stacks.getFavStacks().size(), ccFav, windowSize);
        createStackView(vb_NonFavStacksContainer, stacks.getNonFavStacks(), stacks.getNonFavStacks().size(), ccNonFav, windowSize);
    }


    private void  createStackView(VBox vb, ArrayList<FM_StackManager_Info> stacks, int numOfStacks, ColumnCreator cc, double windowWSize){
        vb.getChildren().clear();

        if(numOfStacks != 0){
            cc = new ColumnCreator(vb, stackViewSize);
            vb.getChildren().clear();

            ArrayList<VBox> vbCol = new ArrayList<>();
            cc.addListener();

            for(FM_StackManager_Info stack: stacks){
                vbCol.add(createStack(stack));
            }

            cc.addVBoxArrayContainersToArray(vbCol);
            cc.createColumns(windowWSize);
        }
    }

    /**
     * Creates Stack Views and Sets Actions
     * @param stack
     * @return
     */
    private VBox createStack(FM_StackManager_Info stack){

        VBox vbAll = new VBox();
        vbAll.getChildren().clear();

        BorderPane bpCardContainer = new BorderPane();
        bpCardContainer.getStyleClass().add("stack");
        bpCardContainer.setMinWidth(BorderPane.USE_COMPUTED_SIZE);

        VBox vbStack = new VBox();
        vbStack.getStyleClass().add("vbContainer");

        Label lbStackTitle = new Label(stack.getStackTitle());
        lbStackTitle.getStyleClass().add("card_title_1");
        vbStack.getChildren().add(lbStackTitle);
        Label lbStackDescription = new Label(stack.getStackDescription());
        lbStackDescription.getStyleClass().add("card_title_2");
        vbStack.getChildren().add(lbStackDescription);
        vbStack.setFillWidth(true);

        bpCardContainer.setCenter(vbStack);
        StackPane.setAlignment(vbStack, Pos.CENTER);

        if (stack.isSelected()){
            VBox stackID = setSelected(stack);
            bpCardContainer.setRight(stackID);
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
        vbAll.getChildren().add(bpCardContainer);

        return vbAll;
    }

    /********** Methods For Stacks **********/
    /**
     * Sets selected styles
     * @param stack
     * @return
     */
    private VBox setSelected(FM_StackManager_Info stack){
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
}
