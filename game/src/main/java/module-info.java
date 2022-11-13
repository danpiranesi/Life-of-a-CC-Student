module com.cclifegame {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cclifegame to javafx.fxml;
    exports com.cclifegame;
}
