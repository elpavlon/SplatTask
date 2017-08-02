package main.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFolderButton extends JButton {
    private File directory;

    public OpenFolderButton(Component parent) {
        super("Open");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setDialogTitle("Choose directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
                    directory = chooser.getSelectedFile();
                }
            }
        });
    }

    public File getDirectory() {
        return directory;
    }
}
