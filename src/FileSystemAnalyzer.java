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
}