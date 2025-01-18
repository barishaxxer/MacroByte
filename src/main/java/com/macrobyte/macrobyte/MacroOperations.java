package com.macrobyte.macrobyte;

import javafx.fxml.FXMLLoader;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MacroOperations {

    ArrayList<String> actionOrder;
    Robot robot;

    public MacroOperations(){

        actionOrder = new ArrayList<>();
        try {
            robot = new Robot();
        }catch(AWTException a){
            System.out.println("Something went wrong");
        }


    }
    public void runMacro(){
        actionOrder.addAll(HelloApplication.controller.getActions());
        for(String s : actionOrder){
            if (s.strip().equals("Left Click")){
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            }else if (s.strip().equals("Right Click")){
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            }

        }

    }
}
