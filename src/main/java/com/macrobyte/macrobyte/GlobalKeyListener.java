package com.macrobyte.macrobyte;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    public static String currentKey;
    private String bufferKey;
    public void nativeKeyPressed(NativeKeyEvent e) {
        bufferKey = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Key Pressed: " + bufferKey);
        if (!bufferKey.equals("Undefined")){
            if (bufferKey.equals("Meta")){
                bufferKey = "Command";
            }
            currentKey = bufferKey;

        }

        if (currentKey.equals(HelloController.startKey)){


                System.out.println(HelloController.startKey);
                System.out.println(GlobalKeyListener.currentKey);

                MacroOperations macro = new MacroOperations();
                macro.runMacro();
                GlobalKeyListener.currentKey = "Undefined";





            }




        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

}
