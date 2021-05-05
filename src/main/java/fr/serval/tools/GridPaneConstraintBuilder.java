package fr.serval.tools;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GridPaneConstraintBuilder {
    public static ColumnConstraints buildGridColumnConstraint(Priority priority, double percentWidth) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(priority);
        columnConstraints.setPercentWidth(percentWidth);
        return columnConstraints;
    }

    public static RowConstraints buildGridRowConstraint(Priority priority, double percentWidth) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(priority);
        rowConstraints.setPercentHeight(percentWidth);
        return rowConstraints;
    }
}
