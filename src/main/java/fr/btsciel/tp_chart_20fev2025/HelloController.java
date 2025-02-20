package fr.btsciel.tp_chart_20fev2025;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import javax.sound.sampled.Line;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public LineChart<Number, Number> Linechart;
    public NumberAxis xAxis;
    public NumberAxis yAxis;
    public Label cursorCoords;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("temps (s)");
        yAxis.setLabel("f(x)");
        cursorCoords.setVisible(false);
        XYChart.Series<Number, Number> serie = new XYChart.Series<>();
        serie.setName("sinus cardinal");

        for (double i = -20; i < 20; i = i + 0.01) {
            if (i != 0) {
                serie.getData().add(new XYChart.Data<Number, Number>(i, (Math.sin(i) / (i))));
            } else {
                serie.getData().add(new XYChart.Data<Number, Number>(i, 1));
            }
        }

        createcursor(Linechart);
        Linechart.setCreateSymbols(false);
        Linechart.getData().add(serie);
    }

    private void createcursor(LineChart<Number, Number> linechart) {
        Axis<Number> xAxis = linechart.getXAxis();
        Axis<Number> yAxis = linechart.getYAxis();
        Node chartBackground = linechart.lookup(".chart-plot-background");

        chartBackground.getParent().getChildrenUnmodifiable().forEach(node -> {
            if (node != chartBackground) {
                node.setMouseTransparent(true);
            } else {
                node.setMouseTransparent(false);
            }
        });
        chartBackground.setOnMouseEntered(event -> {
            cursorCoords.setVisible(true);
        });
        chartBackground.setOnMouseMoved(event -> {
            cursorCoords.setText(xAxis.getValueForDisplay(event.getX()).toString() + "\r" +
                    yAxis.getValueForDisplay(event.getY()).toString());
        });
        chartBackground.setOnMouseExited(event -> {
            cursorCoords.setVisible(false);
        });
    }
}