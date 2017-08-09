package main.components;

import main.FileNavigator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;


public class TabbedPaneManager {
    private JTabbedPane tabbedPane;
    private static TabbedPaneManager tabbedPaneManager = new TabbedPaneManager();

    public static TabbedPaneManager getTabbedPaneManager() {
        return tabbedPaneManager;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private TabbedPaneManager() {
        tabbedPane = new JTabbedPane();
    }

    public void addFileToPane(File file, List<Long> byteNumbers)
    {
        FileNavigator fileNavigator = new FileNavigator(file, byteNumbers);
        JTextArea textArea = new JTextArea(fileNavigator.readFirst());
        textArea.setEditable(false);
        ScrollPane scrollPane = new ScrollPane(textArea, fileNavigator);

        tabbedPane.add(file.getName(), scrollPane);

    }
}
