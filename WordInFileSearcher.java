package main;

import main.components.FolderTreeManager;
import main.components.TabbedPaneManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordInFileSearcher extends Thread {
    private File file;
    private String word;
    private List<Long> byteNumbers;
    private long lineByteNumber;
    private boolean isWordFound;

    public WordInFileSearcher(File file, String word) {
        this.file = file;
        this.word = word;
        byteNumbers = new ArrayList<>();
        lineByteNumber = 0;
        isWordFound = false;
    }

    public List<Long> getByteNumbers() {
        return byteNumbers;
    }

    @Override
    public void run() {
        try(Scanner scanner = new Scanner(file))
        {
            byte[] utf8 = null;
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                if(line.contains(word)) {
                    byteNumbers.add(lineByteNumber);
                    isWordFound = true;
                }
                utf8 = line.getBytes("UTF-8");
                lineByteNumber += utf8.length + 2;
            }
            if(isWordFound) {
                FolderTreeManager.getFolderTreeManager().addFileToTree(file);
                TabbedPaneManager.getTabbedPaneManager().addFileToPane(file, byteNumbers);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No access to file");
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("Encoding exception");
        }
    }
}
