public class Main {
    public static void main(String[] args) {

        Folder root = new Folder("root");
        Folder Documents = new Folder("Documents");
        Folder Pictures = new Folder("Pictures");
        Folder Vacations  = new Folder("Vacations ");


        root.addItem(Documents);
        root.addItem(Pictures);
        Pictures.addItem(Vacations);

        Vacations.addItem(new FileItem("photo1", 7777));
        Vacations.addItem(new FileItem("photo2", 6789));
        Vacations.addItem(new FileItem("photo3", 1357));



        Documents.addItem(new FileItem("ImportantDocumentLOL", 6543));
        Documents.addItem(new FileItem("AnotherDOC", 3939));

        Documents.addItem(new FileItem("RandomPIC1", 9595));
        Documents.addItem(new FileItem("RandomPIC1", 8654));


        System.out.println(FileSystemAnalyzer.calculateTotalSizeRecursive(Documents));
        FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);
        System.out.println("Largest File: " + largest.getName() + " (Size of file: " + largest.getSizeInKB() + " KB)");





        int fileCount = FileSystemAnalyzer.countFilesRecursive(root);
        System.out.println("Total files counted: " + fileCount);
    }
}