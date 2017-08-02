package main.components;

import main.FileSearcher;
import main.ThreadPoolManager;
import main.WordInFileSearcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

public class SearchButton extends JButton {
    public SearchButton(JTextField smallField, JComboBox comboBox, OpenFolderButton open, Component parent) {
        super("Open");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String word = smallField.getText();
                    String extension = (String) comboBox.getSelectedItem();
                    File folder = open.getDirectory();
                    FolderTreeManager.getFolderTreeManager().initializeTree(folder);
                    FileSearcher fileSearcher = new FileSearcher(folder, extension);
                    Collection<File> files = fileSearcher.getFilesFounded();
                    TabbedPaneManager.getTabbedPaneManager().getTabbedPane().removeAll();
                    for (File entry : files) {
                        WordInFileSearcher searcher = new WordInFileSearcher(entry, word);
                        ThreadPoolManager.getThreadPool().submit(searcher);
                    }
                }
                catch(NullPointerException ex)
                {JOptionPane.showMessageDialog(parent, "Enter parameters");}
            }
        });

    }
}
