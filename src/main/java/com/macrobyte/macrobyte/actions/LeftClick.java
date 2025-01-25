package com.macrobyte.macrobyte.actions;

public record LeftClick() implements Action{

    @Override
    public java.lang.String getName() {
        return getClass().getSimpleName();
    }
}
