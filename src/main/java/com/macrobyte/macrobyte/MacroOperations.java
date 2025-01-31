package com.macrobyte.macrobyte;


import com.macrobyte.macrobyte.actions.Action;
import com.macrobyte.macrobyte.actions.MoveCursor;
import com.macrobyte.macrobyte.actions.Sleep;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.List;

public class MacroOperations {

    List<Action> actionOrder;
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
            for (Action s : actionOrder) {
                if(s == null){continue;}
                if (s.getName().strip().equals("LeftClick")) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                } else if (s.getName().strip().equals("RightClick")) {
                    robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                } else if (s.getName().strip().equals("Sleep")) {
                    sleepTime = ((Sleep)s).seconds();
                    try {
                        Thread.sleep(sleepTime * 1000L);
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                } else if (s.getName().strip().equals("MoveCursor")) {
                    robot.mouseMove(((MoveCursor)s).x(), ((MoveCursor)s).y());

                }

            }
        }


    }
}
