package com.macrobyte.macrobyte;


import com.macrobyte.macrobyte.actions.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController {


    @FXML
    public ListView<Action> selectedActions;


    private final String RED_BUTTON = "-fx-background-color: linear-gradient(to right, #f0f2f0, #000c40);" +
            "-fx-border-radius: 102;" +
            "-fx-background-radius: 102;";
    private final String GREEN_BUTTON = "-fx-background-color: linear-gradient(to right, #1f4037, #99f2c8);" +
            "-fx-border-radius: 102;" +
            "-fx-background-radius: 102;";

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

    private final List<String> keys = new ArrayList<>();


    @FXML
    private void initialize() {

        actions.getItems().addAll("Right Click", "Left Click", "Simulate Key", "Sleep", "Move Cursor");
        selectedActions.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Action item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(
                            "-fx-background-color:linear-gradient(to bottom, #1d4a59, #7a3734);" +

                                    "-fx-text-fill: #FFFFFF;" +
                                    "-fx-control-inner-background: #FFFFFF;" +

                                    "-fx-border-radius: 9;" +
                                    "-fx-background-radius: 9;" +
                                    "-fx-background-insets: 18;"
                    );
                } else {
                    setText(item.getPrintName());
                    setStyle(
                            "-fx-background-color:linear-gradient(to bottom, #1d4a59, #7a3734);" +
                                    "-fx-text-fill: #FFFFFF;" +


                                    "-fx-background-insets: 18;" +
                                    "-fx-control-inner-background: #FFFFFF;" +

                                    "-fx-border-radius: 9;" +
                                    "-fx-background-radius: 9;"
                    );
                }
            }
        });
        actions.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(
                            "-fx-background-color:linear-gradient(to bottom, #7a3734, #1d4a59);" +

                                    "-fx-background-insets: 18;" +
                                    "-fx-text-fill: #FFFFFF;" +
                                    "-fx-control-inner-background: #FFFFFF;" +
                                    "-fx-border-radius: 9;" +
                                    "-fx-background-radius: 9;"
                    );
                } else {
                    setText(item);
                    setStyle(
                            "-fx-background-color:linear-gradient(to bottom, #7a3734, #1d4a59);" +
                                    "-fx-background-insets: 18;" +

                                    "-fx-border-width: 2px;" +
                                    "-fx-text-fill: #FFFFFF;" +
                                    "-fx-control-inner-background: #FFFFFF;" +
                                    "-fx-border-radius: 9;" +
                                    "-fx-background-radius: 9;"
                    );
                }
            }
        });

    }


    @FXML
    protected void addItem() {
        String name = actions.getSelectionModel().getSelectedItem();
        switch (name) {
            case "Move Cursor" -> getCoordinatesFromUser();
            case "Simulate Key" -> getKeyFromUser();
            case "Left Click" -> selectedActions.getItems().add(new LeftClick());
            case "Right Click" -> selectedActions.getItems().add(new RightClick());
            case "Sleep" -> selectedActions.getItems().add(new Sleep(getSleep()));
        }

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

    public List<Action> getActions() {
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
                       String content;
                    if (e.getMessage().equals("For input string: \"\"")){
                        content = "The Loop field must not be empty.";
                    }else{
                        content = "The loop field can only be an integer.";
                    }
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setTitle("MacroByte");
                    a.setHeaderText("Invalid input");
                    a.setContentText(content);
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

    // directly adds the MoveCursor action to selectedActions
    private void getCoordinatesFromUser() {
        final int[] coordinates = new int[2];

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
            coordinates[0] = Integer.parseInt(xCoordinate.getText());
            coordinates[1] = Integer.parseInt(yCoordinate.getText());
            selectedActions.getItems().add(new MoveCursor(coordinates));
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

    // directly adds the SimulateKey action to selectedActions
    private void getKeyFromUser() {
        Stage second = new Stage();
        second.initModality(Modality.APPLICATION_MODAL);
        second.setTitle("Keys");
        Label keyLabel = new Label("key");
        TextField key = new TextField();
        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(15);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(e -> {
            selectedActions.getItems().add((new SimulateKey(key.getText().toUpperCase())));
            second.close();
        });
        Button capture = new Button("Capture");
        capture.setOnAction(e -> {
            pane.setOnKeyPressed(keyEvent -> {
                key.setText(keyEvent.getCode().getName());
            });
        });

        pane.add(keyLabel, 2, 0);
        pane.add(key, 2, 1);
        pane.add(confirm, 3, 3);
        pane.add(capture, 3, 1);
        Scene scene = new Scene(pane, 400, 150);
        second.setScene(scene);
        second.show();
    }

    public HashMap<String, Integer> getCoordinates() {
        return coordinates;
    }

    public List<String> getKeys() {
        return keys;
    }

    @FXML
    protected void triggerMacro() {
        MacroOperations macro = new MacroOperations();
        macro.robot.mouseMove(600, 600);
        macro.runMacro();
    }


}