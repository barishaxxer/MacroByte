package com.macrobyte.macrobyte.actions;

public record SimulateKey(String key) implements Action {
    @Override
    public java.lang.String getName() {
        return getClass().getSimpleName();
    }

    public String getPrintName(){
        return "Simulate Key[" + key + "]";
    }
}
