package com.macrobyte.macrobyte;



import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.List;

public class MacroOperations {

    List<String> actionOrder;
    int loopTime;
    int sleepTime;
    HashMap<String, Integer> coordinates;

    Robot robot;


    public MacroOperations() {



        try {
            robot = new Robot();
        } catch (AWTException a) {
            System.out.println("Something went wrong");
        }


    }

    public void runMacro() {
        this.coordinates = HelloApplication.controller.getCoordinates();
        actionOrder = HelloApplication.controller.getActions();
        loopTime = HelloApplication.controller.loopField();

        HelloApplication.controller.notifyUser();
        for (int i = 0; i < loopTime; i++) {
            int track = 0;
            for (String s : actionOrder) {
                if (s.strip().equals("Left Click")) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                } else if (s.strip().equals("Right Click")) {
                    robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                } else if (s.strip().equals("Sleep")) {
                    sleepTime = HelloApplication.controller.getSleep();
                    try {
                        Thread.sleep(sleepTime * 1000);
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                } else if (s.strip().equals("Move Cursor")) {
                    robot.mouseMove(coordinates.get("xCoordinate" + track), coordinates.get("yCoordinate" + track));
                    track++;

                }

            }
        }


    }
}
