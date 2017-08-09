package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.ListIterator;

public class FileNavigator {
    private RandomAccessFile scanner;
    private long startpointer;
    private long endpointer;
    private ListIterator<Long> iterator;

    public FileNavigator(File file, List<Long> byteNumbers) {
        iterator = byteNumbers.listIterator();
        try{
            scanner = new RandomAccessFile(file, "r");
        }

        catch (IOException e) {
            System.out.println("no access to file");
        }
    }

    public String readFirst(){
        return readScreenFrom(iterator.next());
    }

    public String readPreviousScreen(){

        StringBuilder sb = new StringBuilder();
        try {
            if (startpointer<2) return null;
            scanner.seek(startpointer);
            String line = scanner.readLine();
            sb.append(line);
            endpointer = scanner.getFilePointer();
            scanner.seek(startpointer);
            int lineCount = 1;

            while (lineCount < 31) {
                line = readPreviousLine();
                if(line == null) break;
                lineCount += 1;
                sb.insert(0, line);
            }
            startpointer = scanner.getFilePointer();
            while(lineCount<31){
                sb.insert(0, "\n");
                lineCount++;
            }
        }
        catch(IOException e)
        {
            System.out.println("No access to file");
        }
        return sb.toString();
    }

    public String readNextScreen(){
        StringBuilder sb = new StringBuilder();
        try{
            if (endpointer==scanner.length()) return null;
            scanner.seek(endpointer);
            sb.append(readPreviousLine());
            startpointer = scanner.getFilePointer();
            scanner.seek(endpointer);
            int lineCount = 1;
            String line = scanner.readLine();
            while (line != null) {
                lineCount ++;
                sb.append(line);
                if (lineCount > 30) break;
                sb.append("\n");
                line = scanner.readLine();
            }
            endpointer = scanner.getFilePointer();
        }
        catch (IOException e)
        {
            System.out.println("no access to file");
        }
        return sb.toString();
    }

    public String toNextWord(){
        if (!iterator.hasNext()) return null;
        else return readScreenFrom(iterator.next());
    }

    public String toPreviousWord(){
        if (!iterator.hasPrevious()) return null;
        else return readScreenFrom(iterator.previous());
    }

    private String readScreenFrom(long point)
    {
        StringBuilder sb = new StringBuilder();
        try{
            int lineCount = 0;
            startpointer = point;
            scanner.seek(startpointer);
            String line = scanner.readLine();
            while (line != null) {
                lineCount += 1;
                sb.append(line);
                if (lineCount > 30) break;
                sb.append("\n");
                line = scanner.readLine();
            }
            endpointer = scanner.getFilePointer();
        }
        catch (IOException e)
        {
            System.out.println("no access to file");
        }
        return sb.toString();
    }

    private String readPreviousLine() throws IOException{
        StringBuilder builder = new StringBuilder();
        long point = scanner.getFilePointer();
        if(point>1) {
            char c = '\n';
            builder.append(c);
            scanner.seek(point - 2);
            for (long p = scanner.getFilePointer(); p >= 0; p--) {
                scanner.seek(p);
                c = (char) scanner.read();
                if (c == '\n') {
                    break;
                }
                builder.append(c);
            }
            builder.reverse();
            return builder.toString();
        } else return null;

    }

}
