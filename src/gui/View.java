package gui;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.*;

public abstract class View {
    Parent mainLayout;
    Controller controller;
    StackPane popUpBase;


    public View() {

    }

    public Parent getRoot() {
        return mainLayout;
    }

    public Controller getController() {
        return controller;
    }

    public StackPane getPopUpBase() {
        return popUpBase;
    }

    public void setPopUpBase(StackPane popUpBase) {
        this.popUpBase = popUpBase;
    }
}


