package main.components;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public void addFileToPane(File file)
    {
        try(Scanner scanner = new Scanner(file))
        {
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNext())
            {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            tabbedPane.add(file.getName(), new JScrollPane(textArea));
        }
        catch(FileNotFoundException e)
        {e.printStackTrace();}
    }
}
