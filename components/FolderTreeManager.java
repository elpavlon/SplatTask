package main.components;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Enumeration;

public class FolderTreeManager{
private static JTree folderTree;
private static String rootPath;
private static DefaultMutableTreeNode rootNode;
private static DefaultTreeModel treeModel;

    private static FolderTreeManager folderTreeManager = new FolderTreeManager(new File("no files"));


    public static FolderTreeManager getFolderTreeManager() {
        return folderTreeManager;
    }

    public void initializeTree(File rootFolder) {
        rootPath = rootFolder.toString();
        rootNode = new DefaultMutableTreeNode(rootFolder.getName());
        treeModel = new DefaultTreeModel(rootNode, true);
        folderTree.setModel(treeModel);
    }

    private FolderTreeManager(File rootFolder) {
        rootPath = rootFolder.toString();
        rootNode = new DefaultMutableTreeNode(rootFolder.getName());
        treeModel = new DefaultTreeModel(rootNode, true);
        folderTree = new JTree(treeModel);
    }

    public void addFileToTree(File file)
    {
        String path = file.toString();
        path = path.replace(rootPath + "\\", "");
        String[] files = path.split("\\\\");
        DefaultMutableTreeNode parentNode = rootNode;
        for (String filename: files) {
        parentNode = addChild(parentNode, filename);
        }
        folderTree.setModel(new DefaultTreeModel(rootNode));

    }

    private DefaultMutableTreeNode addChild(DefaultMutableTreeNode parent, String child)
    {
        DefaultMutableTreeNode result;
        if (!parent.isLeaf()) {
            Enumeration children = parent.children();
            while (children.hasMoreElements()) {
                result = (DefaultMutableTreeNode) children.nextElement();
                if (result.toString().equals(child)) {
                    return result;
                }
            }
        }
        result = new DefaultMutableTreeNode(child); parent.add(result);

        return result;
    }

    public static JTree getFolderTree() {
        return folderTree;
    }
}
