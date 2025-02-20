module fr.btsciel.tp_chart_20fev2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.btsciel.tp_chart_20fev2025 to javafx.fxml;
    exports fr.btsciel.tp_chart_20fev2025;
}