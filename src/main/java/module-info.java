module com.example.algor3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algor3 to javafx.fxml;
    exports com.example.algor3;
}