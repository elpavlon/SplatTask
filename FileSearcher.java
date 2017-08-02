package main;

import java.io.File;
import java.util.*;

public class FileSearcher {
    private File folder;
    private String fileExtension;
    private List<File> filesFounded = new LinkedList<File>();

    public FileSearcher(File folder, String fileExtension) {
        this.folder = folder;
        this.fileExtension = fileExtension;
    }

    public Collection<File> getFilesFounded()
    {
        searchFolder(folder);
        return filesFounded;}

    private void searchFolder(File folder)
    {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                searchFolder(entry);
            }
            else if(entry.getName().endsWith(fileExtension))
                filesFounded.add(entry);
        }
    }
}
