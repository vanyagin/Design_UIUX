module com.example.firstfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;
}