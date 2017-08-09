package main.components;

import main.FileNavigator;

import javax.swing.*;
import java.awt.*;

public class ScrollPane extends JScrollPane {
    private FileNavigator fileNavigator;
    private JTextArea textArea;

    public ScrollPane(Component view, FileNavigator fileNavigator) {
        super(view);
        this.fileNavigator = fileNavigator;
        this.textArea = (JTextArea) view;
    }

    public FileNavigator getFileNavigator() {
        return fileNavigator;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
