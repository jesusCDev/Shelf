package ControllerUI.DefaultMethods;

import assets.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.prefs.Preferences;
public class Common_ControllerMethods implements Constants{


    private Preferences pref = Preferences.userRoot();

    /*************************************************
     // USER INTERACTION METHODS
     *************************************************/
    public void toast_Message(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void toast_Confirmation(String message){

    }

    public void toast_Options(String message){

    }
    /*************************************************
     // SCREEN METHODS
     *************************************************/

    public void screen_checkAlwaysOnTop(String prefID, MouseEvent event, String fxmlScreen, BorderPane bpAll){
        if(pref.getBoolean(prefID, false)){
            screen_changeDynamicAlwaysOnTop(event, fxmlScreen, bpAll);
        }else{
            screen_changeDynamicAlwaysOffTop(event, fxmlScreen, bpAll);
        }
    }
    public void screen_checkAlwaysOnTop(String prefID, ActionEvent event, String fxmlScreen, BorderPane bpAll){
        if(pref.getBoolean(prefID, false)){
            screen_changeDynamicAlwaysOnTop(event, fxmlScreen, bpAll);
        }else{
            screen_changeDynamicAlwaysOffTop(event, fxmlScreen, bpAll);
        }
    }

    protected void screen_SetSize(BorderPane bodyPane){

        if(!pref.getBoolean(PREF_SV_ScreenMax, false)){
            bodyPane.setPrefWidth(pref.getDouble(PREF_SV_ScreenWidth, 800.0));
            bodyPane.setPrefHeight(pref.getDouble(PREF_SV_ScreenHeight, 800.0));
        }
    }

    public void screen_changeNormal(ActionEvent e, String fxmlScreen){
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeNormal(MouseEvent e, String fxmlScreen){
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeDynamicAlwaysOnTop(MouseEvent e, String fxmlScreen, BorderPane bpAll){
        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(true);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeDynamicAlwaysOnTop(ActionEvent e, String fxmlScreen, BorderPane bpAll){
        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(true);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeDynamicAlwaysOffTop(MouseEvent e, String fxmlScreen, BorderPane bpAll){
        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(false);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeDynamicAlwaysOffTop(ActionEvent e, String fxmlScreen, BorderPane bpAll){
        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(false);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeNormalTurnOfAlwaysOnTop(ActionEvent e, String fxmlScreen){
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(false);

        stage.setScene(scene);
        stage.show();
    }


    public void screen_changeNormalAlwaysOnTop(ActionEvent e, String fxmlScreen){
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(true);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    // TODO MAYBE I SHOULDNT BE TAKING OFF AND ON THE ALWAYS ON TOP FEATURE - TO INCONSISTANT

    public void screen_changeNormalAlwaysOnTop(MouseEvent e, String fxmlScreen){
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setAlwaysOnTop(true);

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    private void changeDynamic_SaveWidth(BorderPane bpAll) {
        pref.putDouble(PREF_SV_ScreenWidth, bpAll.getWidth());
        pref.putDouble(PREF_SV_ScreenHeight, bpAll.getHeight());
    }

    public void screen_changeDynamic(ActionEvent e, String fxmlScreen, BorderPane bpAll){

        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void screen_changeDynamic(MouseEvent e, String fxmlScreen, BorderPane bpAll){

        changeDynamic_SaveWidth(bpAll);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(getClass().getResource(FILE_CSS).toExternalForm());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            pref.putBoolean(PREF_SV_ScreenMax, true);
        }else{
            pref.putBoolean(PREF_SV_ScreenMax, false);
        }

        stage.setScene(scene);
        stage.show();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    /// COMMENTS TO DELETE
    ////////////////////////////////////////////////////////////////////////////////////////////

    private void space(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
    }

    // PROGRAMMING METHODS
    private void pop(String message){
        System.out.println(message);
    }
}
