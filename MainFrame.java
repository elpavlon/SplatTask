package main;

import main.components.FolderTreeManager;
import main.components.OpenFolderButton;
import main.components.SearchButton;
import main.components.TabbedPaneManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private static final String[] extensions = {".log", ".txt", ".xml", ".properties", ".java"};

    MainFrame(){
        super("p.logachev_task1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel label1 = new JLabel("Enter word to find:");

        JTextField smallField = new JTextField(15);
        smallField.setToolTipText("word to find");

        JLabel label2 = new JLabel("Choose file extension:");

        JComboBox comboBox = new JComboBox(extensions);

        JLabel label3 = new JLabel("Choose directory:");

        OpenFolderButton open = new OpenFolderButton(this);

        JButton search = new SearchButton(smallField, comboBox, open, this);

        JScrollPane scrollPane = new JScrollPane(FolderTreeManager.getFolderTreeManager().getFolderTree());

        JTabbedPane tabbedPane = TabbedPaneManager.getTabbedPaneManager().getTabbedPane();


        scrollPane.setLocation(0,0);
        scrollPane.setSize(250,570);
        container.add(scrollPane);

        label1.setLocation(260,0);
        label1.setSize(150,50);
        container.add(label1);

        smallField.setLocation(375,15);
        smallField.setSize(150,20);
        container.add(smallField);

        label2.setLocation(260, 25);
        label2.setSize(150, 50);

        comboBox.setLocation(400, 40);
        comboBox.setSize(124, 20);

        label3.setLocation(260, 50);
        label3.setSize(150, 50);

        open.setLocation(415, 65);
        open.setSize(109, 20);

        search.setLocation(320, 110);
        search.setSize(120, 25);

        tabbedPane.setLocation(550,0);
        tabbedPane.setSize(350, 570);

        container.add(label2);
        container.add(comboBox);
        container.add(label3);
        container.add(open);
        container.add(search);
        container.add(tabbedPane);
        setContentPane(container);
        setResizable(false);
        setSize(900, 600);
    }


    public static void main(String[] args) {

        JFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
