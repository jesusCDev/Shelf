package ControllerUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ToastCreator {

    Text text;
    Timeline timeline;

    private StackPane sp;
    public ToastCreator(StackPane sp){
        this.sp = sp;
    }

    public void createToast(String message){
        sp.getChildren().clear();

        text = new Text(message);
        text.setStyle("-fx-font-size: 0");
        sp.getChildren().add(text);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae -> deleteToast()));
        timeline.play();

    }

    public void deleteToast(){
        sp.getChildren().clear();
        timeline.stop();
    }
}
