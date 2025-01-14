package com.macrobyte.macrobyte;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.macrobyte.macrobyte.HelloApplication;
import java.util.EventListener;


public class HelloController {



    private final String RED_BUTTON = "-fx-background-color:  #c95149;";
    private final String GREEN_BUTTON = "-fx-background-color: green;";

    private KeyCode startKey;

    @FXML
    private ListView<String> actions;

    @FXML
    public Button start;

    @FXML
    private  ListView<String> selectedActions;


    @FXML
    private  void initialize(){

        actions.getItems().addAll("Right Click", "Left Click", "Simulate Key", "Sleep", "Move Cursor");



    }



    @FXML
    protected void addItem(){

        selectedActions.getItems().add(actions.getSelectionModel().getSelectedItem());



    }

    @FXML
    protected void setRed(){

        start.setStyle(RED_BUTTON);
    }
    @FXML
    protected void  setGreen(){
        start.setStyle(GREEN_BUTTON);
    }

    @FXML
    protected void setShortcut(){
        start.setText(HelloApplication.key.getName());
        startKey = HelloApplication.key;


    }









}