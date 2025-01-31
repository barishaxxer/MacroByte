package com.macrobyte.macrobyte.actions;

public record LeftClick() implements Action{

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    public String getPrintName(){
        return "Left Click";
    }
}
