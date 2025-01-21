package com.macrobyte.macrobyte;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;
import java.util.Map;

public class GlobalKeyListener implements NativeKeyListener {
    public static String currentKey;
    private static Map<String, String> keyMap = new HashMap<>();
    static {
        keyMap.put("⏎", "Enter");
        keyMap.put("⌫", "Backspace");
        keyMap.put("⇪", "Caps Lock");
        keyMap.put("⇧", "Shift");
        keyMap.put("⌃", "Ctrl");
        keyMap.put("⌘", "Command");
        keyMap.put("⌥", "Alt");
        keyMap.put("⎋", "Esc");
        keyMap.put("AltGr", "Alt");

    }
    private String bufferKey;
    MacroOperations macro = new MacroOperations();

    public void nativeKeyPressed(NativeKeyEvent e) {
        bufferKey = NativeKeyEvent.getKeyText(e.getKeyCode());
        bufferKey = keyMap.getOrDefault(bufferKey, bufferKey);
        System.out.println("Key Pressed: " + bufferKey);
        if (!bufferKey.equals("Undefined")){

            currentKey = bufferKey;

        }

        if (currentKey != null && currentKey.equals(HelloController.startKey)){


                System.out.println(HelloController.startKey);
                System.out.println(GlobalKeyListener.currentKey);
                GlobalKeyListener.currentKey = "und";
                if (macro != null){
                    macro.runMacro();
                }else {
                    System.out.println("macro is null");
                }






            }else{
            System.out.println("currentkey is null");
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
