package com.macrobyte.macrobyte.actions;

public record Sleep(int seconds) implements Action {
    @Override
    public java.lang.String getName() {
        return getClass().getSimpleName();
    }

    public String getPrintName(){
        return "Sleep[" + seconds + "]";
    }
}
