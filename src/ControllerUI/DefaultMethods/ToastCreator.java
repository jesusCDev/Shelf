package ControllerUI.DefaultMethods;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ToastCreator {

    Timeline timeline;

    private StackPane sp;
    public ToastCreator(StackPane sp){
        this.sp = sp;
        sp.getStyleClass().add("toast");
    }

    public void createShortToast(String message){
        deleteToast();

        sp.getChildren().clear();
        Label toastMessage = new Label(message);
        toastMessage.setStyle("-fx-font-size: 0");
        sp.getChildren().add(toastMessage);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> deleteToast()));
        timeline.play();

    }

    public void crearteLongToast(String message){
        deleteToast();

        sp.getChildren().clear();
        Label toastMessage = new Label(message);
        toastMessage.setStyle("-fx-font-size: 0");
        sp.getChildren().add(toastMessage);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(6000),
                ae -> deleteToast()));
        timeline.play();

    }

    private void deleteToast(){
        sp.getChildren().clear();
        try{
            timeline.stop();
        } catch(NullPointerException e){
        }
    }
}
