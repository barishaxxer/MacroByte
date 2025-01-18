package com.macrobyte.macrobyte;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;


public class HelloController {


    @FXML
    public ListView<String> selectedActions;


    private final String RED_BUTTON = "-fx-background-color:  #c95149;";
    private final String GREEN_BUTTON = "-fx-background-color: green;";

    public static String startKey = "Undfined";

    @FXML
    private ListView<String> actions;

    @FXML
    public Button start;




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
        GlobalKeyListener.currentKey = "Undefined777";
        start.setText(HelloApplication.key.getName());
        startKey = HelloApplication.key.getName();


    }

    public List<String> getActions(){
        return selectedActions.getItems();

    }








}