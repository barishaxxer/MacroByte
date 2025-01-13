module com.macrobyte.macrobyte {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.macrobyte.macrobyte to javafx.fxml;
    exports com.macrobyte.macrobyte;
}