module net.incandescently.wordlesolver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.incandescently.wordlesolver to javafx.fxml;
    exports net.incandescently.wordlesolver;
}