package main.components;

import main.FileNavigator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PreviousScreenButton extends JButton {
    public PreviousScreenButton() {
        super("<");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ScrollPane component = (ScrollPane) TabbedPaneManager.getTabbedPaneManager().getTabbedPane().getSelectedComponent();
                String text = component.getFileNavigator().readPreviousScreen();
                if(text != null) {
                    JTextArea textArea = component.getTextArea();
                    textArea.setText(text);
                }
            }
        });
    }
}
