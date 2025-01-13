package com.macrobyte.macrobyte;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {

    @FXML
    private ListView<String> actions;

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

}