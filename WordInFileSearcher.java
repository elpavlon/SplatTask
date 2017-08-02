package main;

import main.components.FolderTreeManager;
import main.components.TabbedPaneManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordInFileSearcher extends Thread {
    private File file;
    private String word;

    public WordInFileSearcher(File file, String word) {
        this.file = file;
        this.word = word;
    }

    @Override
    public void run() {
        try(Scanner scanner = new Scanner(file))
        {
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                if(line.contains(word)) {
                    FolderTreeManager.getFolderTreeManager().addFileToTree(file);
                    TabbedPaneManager.getTabbedPaneManager().addFileToPane(file);
                    break;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No access to file");
        }
    }
}
