package main.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextScreenButton extends JButton {
    public NextScreenButton() {
        super(">");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScrollPane component = (ScrollPane) TabbedPaneManager.getTabbedPaneManager().getTabbedPane().getSelectedComponent();
                String text = component.getFileNavigator().readNextScreen();
                if(text != null) {
                    JTextArea textArea = component.getTextArea();
                    textArea.setText(text);
                }
            }
        });
    }
}