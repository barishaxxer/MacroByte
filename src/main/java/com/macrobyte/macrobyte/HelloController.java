package com.macrobyte.macrobyte;


import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


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
    public TextField sleepVariable;

    private HashMap<String, Integer> coordinates = new HashMap<>();

    private int tracker = 0;
    private int x;
    private int y;


    @FXML
    private void initialize() {

        actions.getItems().addAll("Right Click", "Left Click", "Simulate Key", "Sleep", "Move Cursor");
        selectedActions.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                while (change.next()) {
                    for (String s : change.getAddedSubList()) {

                        if (s.strip().equals("Move Cursor")) {
                            getCoordinatesFromUser();
                        }

                    }
                }

            }
        });

    }


    @FXML
    protected void addItem() {

        selectedActions.getItems().add(actions.getSelectionModel().getSelectedItem());


    }

    @FXML
    protected void setRed() {

        start.setStyle(RED_BUTTON);
    }

    @FXML
    protected void setGreen() {
        start.setStyle(GREEN_BUTTON);
    }

    @FXML
    protected void setShortcut() {
        GlobalKeyListener.currentKey = "Undefined777";
        start.setText(HelloApplication.key.getName());
        startKey = HelloApplication.key.getName();


    }

    public List<String> getActions() {
        return selectedActions.getItems();

    }

    public int loopField() {
        int returnValue = 1;
        try {
            if (loopNumber.getText() != null) {
                returnValue = Integer.parseInt(loopNumber.getText());
            }
        } catch (NumberFormatException e) {
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

    public void notifyUser() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                banner.setText("    MacroByte | Running");
            }
        });
    }

    public int getSleep() {
        return Integer.parseInt(sleepVariable.getText());

    }

    private void getCoordinatesFromUser() {
        Stage second = new Stage();
        second.initModality(Modality.APPLICATION_MODAL);
        second.setTitle("Coordinates");
        Label xLabel = new Label("x coordinate");
        TextField xCoordinate = new TextField();
        Label yLabel = new Label(("y coordinate"));
        TextField yCoordinate = new TextField();
        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(15);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(e -> {
            coordinates.put("xCoordinate" + tracker,Integer.parseInt(xCoordinate.getText()));
            coordinates.put("yCoordinate" + tracker, Integer.parseInt(yCoordinate.getText()));
            tracker++;

            second.close();


        });
        pane.add(xLabel, 0, 0);
        pane.add(xCoordinate, 0, 1);
        pane.add(yLabel, 2, 0);
        pane.add(yCoordinate, 2, 1);
        pane.add(confirm, 1, 3);
        Scene scene = new Scene(pane, 400, 150);
        second.setScene(scene);
        second.show();


    }

    public HashMap<String, Integer> getCoordinates(){

        return coordinates;
    }


}