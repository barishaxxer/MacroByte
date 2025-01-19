package com.macrobyte.macrobyte;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;


public class HelloController {


    @FXML
    public ListView<String> selectedActions;


    private final String RED_BUTTON = "-fx-background-color:  #c95149;";
    private final String GREEN_BUTTON = "-fx-background-color: green;";

    public static String startKey = "Undfined";
    private Alert a = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<String> actions;

    @FXML
    private Label banner;

    @FXML
    public Button start;

    @FXML
    public TextField loopNumber;




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
    public int loopField(){
        int returnValue = 1;
        try {
            if (loopNumber.getText() != null) {
                returnValue = Integer.parseInt(loopNumber.getText());
            }
        }catch(NumberFormatException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setTitle("MacroByte");
                    a.setHeaderText("Invalid input");
                    a.setContentText("The loop field can be only integer.");
                    a.show();
                }
            });


        }
        return returnValue;
    }

    public void notifyUser(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                banner.setText("    MacroByte | Running");
            }
        });
    }






}