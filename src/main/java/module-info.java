module com.macrobyte.macrobyte {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.github.kwhat.jnativehook;


    opens com.macrobyte.macrobyte to javafx.fxml;
    exports com.macrobyte.macrobyte;

}