module net.incandescently.worldesolver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens net.incandescently.worldesolver to javafx.fxml;
    exports net.incandescently.worldesolver;
}