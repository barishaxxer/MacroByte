package com.macrobyte.macrobyte.actions;

public record RightClick() implements Action {
    @Override
    public java.lang.String getName() {
        return getClass().getSimpleName();
    }
}
