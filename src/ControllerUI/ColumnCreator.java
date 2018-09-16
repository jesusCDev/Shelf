package ControllerUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * This Class Handles The Cards In The Given Area and Their Resizing
 */
public class ColumnCreator {

    ChangeListener cl;
    private int lastGridNumber = 2;
    private VBox container;
    private int colSize;

    private ArrayList<VBox> vbContainer = new ArrayList<>();

    public ColumnCreator(){

    }

    public void setDefault(VBox vb){

    }

    public ColumnCreator(VBox container, int colSize){
        this.container = container;
        this.colSize = colSize;
    }

    public void createColumns(double windowSize){
        container.getChildren().add(recreateGridPaneWithButtons(container, ((int)Math.floor((windowSize / colSize)))));
    }


    /**
     * Recreates gird pane every time the size of its container is changed
     * @param container its container (clears it and resets position)
     * @param colNum number of cols
     * @return retuns gridpane for which is add into the container
     */
    private VBox recreateGridPaneWithButtons(VBox container, int colNum){
        // Clean up
        container.getChildren().clear();
        container.setAlignment(Pos.TOP_CENTER);

        VBox newContainer = new VBox();
        // Grid pane and vbox settings
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: pink;");
        gp.setAlignment(Pos.TOP_CENTER);
        gp.setHgap(10.0);
        gp.setVgap(10.0);

        int rows = 0;
        int pos = 0;
        do{
            for(int j = 0; j < colNum; j++){
                gp.add(vbContainer.get(pos),j,rows);

                pos++;
                if((pos >= vbContainer.size())){
                    break;
                }
            }
            if((pos >= vbContainer.size())){
                break;
            }
            rows++;
        }while(true);
        newContainer.getChildren().addAll(gp);

        return newContainer;
    }

    public void removeListener(){
        container.widthProperty().removeListener(cl);
    }

    public void addListener(){
        cl = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(vbContainer.size() > 0){
                    int currentGirdColumbs = ((int)Math.floor(newValue.intValue()/ colSize));
                    if((currentGirdColumbs != lastGridNumber) && (currentGirdColumbs != 0)){
                        container.getChildren().add(recreateGridPaneWithButtons(container, currentGirdColumbs));
                        lastGridNumber = currentGirdColumbs;
                    }
                }else{
                    container.getChildren().clear();
                }
            }
        };

        container.widthProperty().addListener(cl);
    }
    /**
     * Adds multiple contains to grid
     * @param vb
     */
    public void addVBoxArrayContainersToArray(ArrayList<VBox> vb){
        vbContainer.addAll(vb);
    }

    /**
     * Adds container to grid
     * @param vb
     */
    public void addVBoxContainersToArray(VBox vb){
        vbContainer.add(vb);
    }

    public VBox getContainer(){
        return container;
    }
}
