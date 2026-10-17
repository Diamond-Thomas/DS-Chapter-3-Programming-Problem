import java.util.Stack;

public class FileSystemAnalyzer{
    public static int countFilesRecursive(FileSystemItem item){
        if (item instanceof FileItem) {
            return 1;
        }

        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            int counter = 0;

            for (FileSystemItem child : folder.getItems()) {
                counter += countFilesRecursive(child);
            }

            return counter;
        }

        return 0;
    }

    public static int calculateTotalSizeRecursive(FileSystemItem item){
        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            int totalSize = 0;

            for (FileSystemItem child : folder.getItems()) {
                totalSize += calculateTotalSizeRecursive(child);
            }

            return totalSize;
        }

        return 0;
    }

    public static FileItem findLargestFileRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        if (item instanceof Folder) {
            FileItem largest = null;
            Folder folder = (Folder) item;

            for (FileSystemItem Item : folder.getItems()) {
                FileItem FI = findLargestFileRecursive(Item);

                if (FI != null) {
                    if (largest == null || FI.getSizeInKB() > largest.getSizeInKB()) {
                        largest = FI;
                    }
                }



            } return largest;
        } return null;
    }


    public static int countFilesIterative(Folder rootFolder){

        Stack<FileSystemItem> stack = new Stack<>();
        stack.push(rootFolder);
        int fileCount = 0;

        while (!stack.isEmpty()) {
            FileSystemItem pop = stack.pop();

            if (pop instanceof FileItem){
                fileCount++;
            }
            else if (pop instanceof Folder){

                Folder stackFolder = (Folder) pop;
                for(FileSystemItem Item : stackFolder.getItems()){
                    stack.push(Item);
                }
            }
        }


        return fileCount ;
    }

    public static Folder findFolder(Folder current, String targetName) {
        if (current.getName().equalsIgnoreCase(targetName)) {
            return current;
        }
        for (FileSystemItem child : current.getItems()) {
            if (child instanceof Folder) {
                Folder found = findFolder((Folder) child, targetName);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public static void printHierarchy(FileSystemItem item, String indent) {
        if (item instanceof Folder) {
            System.out.println(indent + item.getName() + "/");
            Folder folder = (Folder) item;
            for (FileSystemItem child : folder.getItems()) {
                printHierarchy(child, indent + "  ");
            }
        } else if (item instanceof FileItem) {
            System.out.println(indent + item.getName() + " (" + item.getSizeInKB() + " KB)");
        }
    }
}