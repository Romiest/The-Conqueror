package gui;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.*;
import java.util.*;

public class ModifiedGridPane extends GridPane {

    public ModifiedGridPane(int columns, int rows) {

        for (int i = 0; i < columns; i++) {
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(100.0/columns);
            this.getColumnConstraints().add(col1);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(100.0/rows);
            this.getRowConstraints().addAll(row1);
        }

    }

}


