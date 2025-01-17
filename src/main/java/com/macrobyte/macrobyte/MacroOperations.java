package com.macrobyte.macrobyte;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

public class MacroOperations {

    ArrayList<String> actionOrder;
    HelloApplication mainClass;

    public MacroOperations(){

        actionOrder = new ArrayList<>();
        mainClass = new HelloApplication();


    }
    public void runMacro(){
        System.out.println(HelloApplication.controller.getActions());

    }
}
